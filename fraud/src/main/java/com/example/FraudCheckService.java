package com.example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public FraudCheckResponse isFraudulentCustomer(final Long customerId) {
        final FraudCheckHistory fraudCheckHistory = FraudCheckHistory.builder()
            .customerId(customerId)
            .isFraudster(false)
            .build();
        fraudCheckHistoryRepository.save(fraudCheckHistory);
        return new FraudCheckResponse(customerId, false);
    }
}
