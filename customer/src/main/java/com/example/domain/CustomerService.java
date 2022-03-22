package com.example.domain;

import com.example.config.RabbitMqMessageProducer;
import com.example.presentation.CustomerRegistrationRequest;
import com.example.presentation.FraudApiCaller;
import com.example.presentation.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudApiCaller fraudApiCaller;
    private final RabbitMqMessageProducer messageProducer;

    @Transactional
    public void register(CustomerRegistrationRequest command) {
        final Customer customer = Customer.builder()
            .firstName(command.firstName())
            .lastName(command.lastName())
            .email(command.email())
            .build();

        customerRepository.save(customer);

        // check if fraudster
        final FraudCheckResponse response = fraudApiCaller.isFraudster(customer.getId());

        if (response.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        // send notification
        final var notification = new NotificationRequest(
            customer.getId(),
            customer.getEmail(),
            "Hello",
            LocalDate.now()
        );

        messageProducer.publish(
            "internal.exchange",
            "internal.notification.routing-key",
            notification
        );
    }
}
