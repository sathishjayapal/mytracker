package me.sathish.garmindatainitializer.data;

import java.math.BigInteger;
import org.springframework.stereotype.Service;

@Service
public class RawGarminRunMapper {
    public GarminRun toEntity(RawActivities rawActivities) {
        GarminRun activities = new GarminRun();
        activities.setActivityID(new BigInteger(rawActivities.getActivityID()));
        activities.setActivityDate(rawActivities.getActivityDate());
        activities.setActivityType(rawActivities.getActivityType());
        activities.setActivityDescription(rawActivities.getActivityDescription());
        activities.setElapsedTime(rawActivities.getElapsedTime());
        activities.setDistance(rawActivities.getDistance());
        activities.setCalories(rawActivities.getCalories());
        activities.setMaxHeartRate(rawActivities.getMaxHeartRate());
        activities.setActivityName(rawActivities.getActivityName());
        return activities;
    }
}
