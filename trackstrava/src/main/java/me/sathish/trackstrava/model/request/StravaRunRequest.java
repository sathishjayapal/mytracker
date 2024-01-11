package me.sathish.trackstrava.model.request;

import jakarta.validation.constraints.NotEmpty;

public record StravaRunRequest(@NotEmpty(message = "Text cannot be empty") String text) {}
