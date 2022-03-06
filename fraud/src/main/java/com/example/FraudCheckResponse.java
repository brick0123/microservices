package com.example;

public record FraudCheckResponse(
    Long consumerId,
    Boolean isFraudster
) {

}
