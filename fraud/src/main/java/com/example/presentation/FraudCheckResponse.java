package com.example.presentation;

public record FraudCheckResponse(
    Long consumerId,
    Boolean isFraudster
) {

}
