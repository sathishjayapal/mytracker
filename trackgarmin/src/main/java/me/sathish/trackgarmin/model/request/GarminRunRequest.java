package me.sathish.trackgarmin.model.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public record GarminRunRequest(
        @NotEmpty(message = "Run Date Time cannot be empty") String runDateTime,
        @Digits(message = "Run miles cannot be less than zero", integer = 4, fraction = 1) BigDecimal runMiles,
        @NotEmpty(message = "Run Name cannot be empty") String runName) {}
