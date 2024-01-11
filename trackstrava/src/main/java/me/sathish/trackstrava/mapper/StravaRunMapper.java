package me.sathish.trackstrava.mapper;

import java.util.List;
import me.sathish.trackstrava.entities.StravaRun;
import me.sathish.trackstrava.model.request.StravaRunRequest;
import me.sathish.trackstrava.model.response.StravaRunResponse;
import org.springframework.stereotype.Service;

@Service
public class StravaRunMapper {

    public StravaRun toEntity(StravaRunRequest stravaRunRequest) {
        StravaRun stravaRun = new StravaRun();
        stravaRun.setText(stravaRunRequest.text());
        return stravaRun;
    }

    public void mapStravaRunWithRequest(StravaRun stravaRun, StravaRunRequest stravaRunRequest) {
        stravaRun.setText(stravaRunRequest.text());
    }

    public StravaRunResponse toResponse(StravaRun stravaRun) {
        return new StravaRunResponse(stravaRun.getId(), stravaRun.getText());
    }

    public List<StravaRunResponse> toResponseList(List<StravaRun> stravaRunList) {
        return stravaRunList.stream().map(this::toResponse).toList();
    }
}
