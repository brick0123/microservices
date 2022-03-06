package com.example.presentation;

public record CustomerRegistrationRequest(
    String firstName,
    String lastName,
    String email
) {

}
