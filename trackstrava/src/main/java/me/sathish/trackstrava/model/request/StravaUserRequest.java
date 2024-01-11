package me.sathish.trackstrava.model.request;

import jakarta.validation.constraints.NotEmpty;

public record StravaUserRequest(
        @NotEmpty(message = "Name cannot be empty") String name,
        @NotEmpty(message = "Email cannot be empty") String email,
        @NotEmpty(message = "phoneNumber cannot be empty") String phoneNumber) {}
