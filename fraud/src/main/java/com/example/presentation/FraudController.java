package com.example.presentation;

import com.example.domain.FraudCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
public record FraudController(
    FraudCheckService fraudCheckService
) {

    @GetMapping("{customerId}")
    public ResponseEntity<?> isFraudster(@PathVariable Long customerId) {
        final FraudCheckResponse result = fraudCheckService.isFraudulentCustomer(customerId);
        log.info(">>> fraud check request for customer = {}", customerId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
