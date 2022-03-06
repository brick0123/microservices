package com.example.domain;

import com.example.presentation.CustomerRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public void register(CustomerRegistrationRequest command) {
        final Customer customer = Customer.builder()
            .firstName(command.firstName())
            .lastName(command.lastName())
            .email(command.email())
            .build();

        customerRepository.save(customer);
    }
}
