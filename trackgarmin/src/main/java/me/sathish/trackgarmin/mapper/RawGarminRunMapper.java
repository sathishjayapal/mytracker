package me.sathish.trackgarmin.mapper;

import java.math.BigInteger;
import me.sathish.entities.RawActivities;
import me.sathish.trackgarmin.entities.GarminRun;
import me.sathish.trackgarmin.model.request.GarminRunRequest;
import org.springframework.stereotype.Service;

@Service
public class RawGarminRunMapper {
    public void mapRawActivitiesWithRequest(RawActivities rawActivities, GarminRunRequest activitiesRequest) {
        rawActivities.setActivityID(activitiesRequest.activityID());
        rawActivities.setActivityDate(activitiesRequest.activityDate());
        rawActivities.setActivityType(activitiesRequest.activityType());
        rawActivities.setActivityDescription(activitiesRequest.activityDescription());
        rawActivities.setElapsedTime(activitiesRequest.elapsedTime());
        rawActivities.setDistance(activitiesRequest.distance());
        rawActivities.setCalories(activitiesRequest.calories());
        rawActivities.setMaxHeartRate(activitiesRequest.maxHeartRate());
        rawActivities.setActivityName(activitiesRequest.activityName());
    }

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
