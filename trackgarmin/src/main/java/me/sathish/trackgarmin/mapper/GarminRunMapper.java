package me.sathish.trackgarmin.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import me.sathish.trackgarmin.entities.GarminRun;
import me.sathish.trackgarmin.model.request.GarminRunRequest;
import me.sathish.trackgarmin.model.response.GarminRunResponse;
import org.springframework.stereotype.Service;

@Service
public class GarminRunMapper {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public GarminRun toEntity(GarminRunRequest garminRunRequest) {
        GarminRun garminRun = new GarminRun();
        garminRun.setRunName(garminRunRequest.runName());
        garminRun.setRunMiles(garminRunRequest.runMiles());
        String inputDateTime = garminRunRequest.runDateTime();
        garminRun.setRunDateTime(LocalDateTime.from(dateTimeFormatter.parse(inputDateTime)));
        return garminRun;
    }

    public void mapGarminRunWithRequest(GarminRun garminRun, GarminRunRequest garminRunRequest) {
        garminRun.setRunName(garminRunRequest.runName());
        garminRun.setRunDateTime(LocalDateTime.parse(garminRunRequest.runDateTime(), dateTimeFormatter));
        garminRun.setRunMiles(garminRunRequest.runMiles());
    }

    public GarminRunResponse toResponse(GarminRun garminRun) {
        LocalDateTime responseRunDateTime = garminRun.getRunDateTime();
        String runDateTimeStr = responseRunDateTime.format(dateTimeFormatter);
        return new GarminRunResponse(
                garminRun.getId(),
                garminRun.getRunName(),
                runDateTimeStr,
                garminRun.getRunMiles().longValue());
    }

    public List<GarminRunResponse> toResponseList(List<GarminRun> garminRunList) {
        return garminRunList.stream().map(this::toResponse).toList();
    }
}
