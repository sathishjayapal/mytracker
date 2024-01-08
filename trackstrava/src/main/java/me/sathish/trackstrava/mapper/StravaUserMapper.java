package me.sathish.trackstrava.mapper;

import java.util.List;
import me.sathish.trackstrava.entities.StravaUser;
import me.sathish.trackstrava.model.request.StravaUserRequest;
import me.sathish.trackstrava.model.response.StravaUserResponse;
import org.springframework.stereotype.Service;

@Service
public class StravaUserMapper {

    public StravaUser toEntity(StravaUserRequest stravaUserRequest) {
        StravaUser stravaUser = new StravaUser();
        stravaUser.setText(stravaUserRequest.text());
        return stravaUser;
    }

    public void mapStravaUserWithRequest(
            StravaUser stravaUser, StravaUserRequest stravaUserRequest) {
        stravaUser.setText(stravaUserRequest.text());
    }

    public StravaUserResponse toResponse(StravaUser stravaUser) {
        return new StravaUserResponse(stravaUser.getId(), stravaUser.getText());
    }

    public List<StravaUserResponse> toResponseList(List<StravaUser> stravaUserList) {
        return stravaUserList.stream().map(this::toResponse).toList();
    }
}
