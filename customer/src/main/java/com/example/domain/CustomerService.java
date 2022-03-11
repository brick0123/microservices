package com.example.domain;

import com.example.presentation.CustomerRegistrationRequest;
import com.example.presentation.FraudApiCaller;
import com.example.presentation.NotificationApiCaller;
import com.example.presentation.NotificationRequest;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final NotificationApiCaller notificationApiCaller;
    private final FraudApiCaller fraudApiCaller;

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
        sendNotification(customer);
    }

    private void sendNotification(Customer customer) {
        notificationApiCaller.sendNotification(
            new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                "Hello",
                LocalDate.now()
            )
        );
    }
}
