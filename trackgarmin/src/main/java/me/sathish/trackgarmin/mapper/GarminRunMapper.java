package me.sathish.trackgarmin.mapper;

import java.util.List;
import me.sathish.trackgarmin.entities.GarminRun;
import me.sathish.trackgarmin.model.request.GarminRunRequest;
import me.sathish.trackgarmin.model.response.GarminRunResponse;
import org.springframework.stereotype.Service;

@Service
public class GarminRunMapper {

    public GarminRun toEntity(GarminRunRequest garminRunRequest) {
        GarminRun garminRun = new GarminRun();
        garminRun.setText(garminRunRequest.text());
        return garminRun;
    }

    public void mapGarminRunWithRequest(GarminRun garminRun, GarminRunRequest garminRunRequest) {
        garminRun.setText(garminRunRequest.text());
    }

    public GarminRunResponse toResponse(GarminRun garminRun) {
        return new GarminRunResponse(garminRun.getId(), garminRun.getText());
    }

    public List<GarminRunResponse> toResponseList(List<GarminRun> garminRunList) {
        return garminRunList.stream().map(this::toResponse).toList();
    }
}
