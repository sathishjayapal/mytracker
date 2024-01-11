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
        stravaUser.setPhoneNumber(stravaUserRequest.phoneNumber());
        stravaUser.setEmail(stravaUserRequest.email());
        stravaUser.setName(stravaUserRequest.name());
        return stravaUser;
    }

    public void mapStravaUserWithRequest(
            StravaUser stravaUser, StravaUserRequest stravaUserRequest) {
        stravaUser.setPhoneNumber(stravaUserRequest.phoneNumber());
        stravaUser.setEmail(stravaUserRequest.email());
        stravaUser.setName(stravaUserRequest.name());
    }

    public StravaUserResponse toResponse(StravaUser stravaUser) {
        return new StravaUserResponse(
                stravaUser.getCustomerId(),
                stravaUser.getName(),
                stravaUser.getEmail(),
                stravaUser.getPhoneNumber());
    }

    public List<StravaUserResponse> toResponseList(List<StravaUser> stravaUserList) {
        return stravaUserList.stream().map(this::toResponse).toList();
    }
}
