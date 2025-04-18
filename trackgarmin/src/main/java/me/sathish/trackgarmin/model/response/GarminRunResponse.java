package me.sathish.trackgarmin.model.response;

public record GarminRunResponse(
        Long id,
        String activityID,
        String activityDate,
        String activityType,
        String activityDescription,
        String elapsedTime,
        String distance,
        String calories,
        String maxHeartRate,
        String activityName) {}
