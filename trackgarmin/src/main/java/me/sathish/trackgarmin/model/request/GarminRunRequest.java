package me.sathish.trackgarmin.model.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public record GarminRunRequest(
        @NotEmpty(message = "ActivityID cannot be empty") String activityID,
        @NotEmpty(message = "ActivityDate cannot be empty") String activityDate,
        @NotEmpty(message = "ActivityType cannot be empty") String activityType,
        String activityDescription,
        @NotEmpty(message = "ElapsedTime cannot be empty") String elapsedTime,
        @NotEmpty(message = "Distance cannot be empty") String distance,
        @NotEmpty(message = "Calories cannot be empty") String calories,
        String maxHeartRate,
        @NotEmpty(message = "ActivityName cannot be empty") String activityName) {}
