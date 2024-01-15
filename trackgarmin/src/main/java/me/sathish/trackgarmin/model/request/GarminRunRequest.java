package me.sathish.trackgarmin.model.request;

import jakarta.validation.constraints.NotEmpty;

public record GarminRunRequest(@NotEmpty(message = "Text cannot be empty") String text) {}
