package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
public record FraudHistoryController(
    FraudCheckService fraudCheckService
) {

    @GetMapping("{customerId}")
    public ResponseEntity<?> isFraudster(@PathVariable Long customerId) {
        final FraudCheckResponse result = fraudCheckService.isFraudulentCustomer(customerId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
