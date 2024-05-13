package me.sathish.trackgarmin.mapper;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import me.sathish.trackgarmin.entities.GarminRun;
import me.sathish.trackgarmin.model.request.GarminRunRequest;
import me.sathish.trackgarmin.model.response.GarminRunResponse;
import org.springframework.stereotype.Service;

@Service
public class GarminRunMapper {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public GarminRun toEntity(GarminRunRequest garminRunRequest) {
        GarminRun garminRun = new GarminRun();
        BigInteger activityID = new BigInteger(garminRunRequest.activityID());
        updateIfDifferent(garminRun.getActivityID(), activityID, garminRun::setActivityID);
        updateIfDifferent(garminRun.getActivityDate(), garminRunRequest.activityDate(), garminRun::setActivityDate);
        updateIfDifferent(garminRun.getActivityType(), garminRunRequest.activityType(), garminRun::setActivityType);
        updateIfDifferent(
                garminRun.getActivityDescription(),
                garminRunRequest.activityDescription(),
                garminRun::setActivityDescription);
        checkMapperData(garminRun, garminRunRequest);
        return garminRun;
    }
    private <T> void updateIfDifferent(T currentValue, T newValue, Consumer<T> setter) {
        if (!Objects.equals(currentValue, newValue)) {
            setter.accept(newValue);
        }
    }
    public void mapGarminRunWithRequest(GarminRun garminRuns, GarminRunRequest garminRunRequest) {
        updateIfDifferent(garminRuns.getActivityName(), garminRunRequest.activityName(), garminRuns::setActivityName);
        updateIfDifferent(garminRuns.getActivityType(), garminRunRequest.activityType(), garminRuns::setActivityType);
        updateIfDifferent(
                garminRuns.getActivityDescription(),
                garminRunRequest.activityDescription(),
                garminRuns::setActivityDescription);
        updateIfDifferent(garminRuns.getActivityDate(), garminRunRequest.activityDate(), garminRuns::setActivityDate);
        checkMapperData(garminRuns, garminRunRequest);
    }
    private void checkMapperData(GarminRun garminRuns, GarminRunRequest garminRunRequest) {
        updateIfDifferent(garminRuns.getElapsedTime(), garminRunRequest.elapsedTime(), garminRuns::setElapsedTime);
        String distance = garminRunRequest.distance();
        if (distance != null) {
            double distanceInKm = Double.parseDouble(distance) * 1.6;
            updateIfDifferent(garminRuns.getDistance(), String.valueOf(distanceInKm), garminRuns::setDistance);
        }
        updateIfDifferent(garminRuns.getCalories(), garminRunRequest.calories(), garminRuns::setCalories);
        updateIfDifferent(garminRuns.getMaxHeartRate(), garminRunRequest.maxHeartRate(), garminRuns::setMaxHeartRate);
        updateIfDifferent(garminRuns.getActivityName(), garminRunRequest.activityName(), garminRuns::setActivityName);
    }
    public GarminRunResponse toResponse(GarminRun garminRuns) {
        return new GarminRunResponse(
                garminRuns.getId(),
                String.valueOf(garminRuns.getActivityID()),
                garminRuns.getActivityDate(),
                garminRuns.getActivityType(),
                garminRuns.getActivityDescription(),
                garminRuns.getElapsedTime(),
                garminRuns.getDistance(),
                garminRuns.getCalories(),
                garminRuns.getMaxHeartRate(),
                garminRuns.getActivityName());
    }

    public List<GarminRunResponse> toResponseList(List<GarminRun> garminRunList) {
        return garminRunList.stream().map(this::toResponse).toList();
    }


}
