package me.sathish.trackstrava.model.request;

import jakarta.validation.constraints.NotEmpty;

public record StravaRunRequest(
        @NotEmpty(message = "Text cannot be empty") String run_name,
        @NotEmpty(message = "Run Date cannot be empty") String run_date,
        int miles,
        long start_location) {}
