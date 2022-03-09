package com.example.domain;

public record FraudCheckResponse(
    Long consumerId,
    Boolean isFraudster
) {

}
