package com.example.domain;

import com.example.presentation.FraudCheckResponse;
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
