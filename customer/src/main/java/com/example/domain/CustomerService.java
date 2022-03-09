package com.example.domain;

import com.example.presentation.CustomerRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
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
        final FraudCheckResponse response = restTemplate.getForObject(
            "http://FRAUD/api/v1/fraud-check/{customerId}",
            FraudCheckResponse.class,
            customer.getId()
        );

        if (response.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        // send
    }
}
