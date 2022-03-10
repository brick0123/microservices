package com.example.domain;

import com.example.presentation.CustomerRegistrationRequest;
import com.example.presentation.FraudApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudApiCaller fraudApiCaller;
    private final RestTemplate restTemplate;

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
    }
}
