package com.example.presentation;

import java.time.LocalDate;

public record NotificationRequest(
    Long customerId,
    String customerEmail,
    String message,
    LocalDate sendAt
) {

}
