package me.sathish.trackstrava.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import me.sathish.trackstrava.entities.StravaRun;
import me.sathish.trackstrava.model.request.StravaRunRequest;
import me.sathish.trackstrava.model.response.StravaRunResponse;
import org.springframework.stereotype.Service;

@Service
public class StravaRunMapper {
    DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public StravaRun toEntity(StravaRunRequest stravaRunRequest) {
        StravaRun stravaRun = new StravaRun();
        stravaRun.setRun_name(stravaRunRequest.run_name());

        stravaRun.setRun_date(LocalDateTime.from(dateTimeFormatter.parse(stravaRunRequest.run_date())));
        stravaRun.setMiles(stravaRunRequest.miles());
        stravaRun.setStart_location(stravaRunRequest.start_location());
        return stravaRun;
    }

    public void mapStravaRunWithRequest(StravaRun stravaRun, StravaRunRequest stravaRunRequest) {
        stravaRun.setRun_name(stravaRunRequest.run_name());
        stravaRun.setRun_date(LocalDateTime.from(dateTimeFormatter.parse(stravaRunRequest.run_date())));
        stravaRun.setMiles(stravaRunRequest.miles());
        stravaRun.setStart_location(stravaRunRequest.start_location());
    }

    public StravaRunResponse toResponse(StravaRun stravaRun) {
        return new StravaRunResponse(stravaRun.getRunNumber(),
            stravaRun.getRun_name(),
            stravaRun.getRun_date(),
            stravaRun.getMiles(),
            stravaRun.getStart_location());
    }

    public List<StravaRunResponse> toResponseList(List<StravaRun> stravaRunList) {
        return stravaRunList.stream().map(this::toResponse).toList();
    }
}
