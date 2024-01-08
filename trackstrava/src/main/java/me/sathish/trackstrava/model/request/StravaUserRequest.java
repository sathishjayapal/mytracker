package me.sathish.trackstrava.model.request;

import jakarta.validation.constraints.NotEmpty;

public record StravaUserRequest(@NotEmpty(message = "Text cannot be empty") String text) {}
