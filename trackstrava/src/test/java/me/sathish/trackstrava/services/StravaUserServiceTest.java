package me.sathish.trackstrava.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.willDoNothing;

import java.util.Optional;
import me.sathish.trackstrava.entities.StravaUser;
import me.sathish.trackstrava.mapper.StravaUserMapper;
import me.sathish.trackstrava.model.response.StravaUserResponse;
import me.sathish.trackstrava.repositories.StravaUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StravaUserServiceTest {

    @Mock private StravaUserRepository stravaUserRepository;
    @Mock private StravaUserMapper stravaUserMapper;

    @InjectMocks private StravaUserService stravaUserService;

    @Test
    void findStravaUserById() {
        // given
        given(stravaUserRepository.findById(1L)).willReturn(Optional.of(getStravaUser()));
        given(stravaUserMapper.toResponse(any(StravaUser.class)))
                .willReturn(getStravaUserResponse());
        // when
        Optional<StravaUserResponse> optionalStravaUser = stravaUserService.findStravaUserById(1L);
        // then
        assertThat(optionalStravaUser).isPresent();
        StravaUserResponse stravaUser = optionalStravaUser.get();
        assertThat(stravaUser.id()).isEqualTo(1L);
        assertThat(stravaUser.text()).isEqualTo("junitTest");
    }

    @Test
    void deleteStravaUserById() {
        // given
        willDoNothing().given(stravaUserRepository).deleteById(1L);
        // when
        stravaUserService.deleteStravaUserById(1L);
        // then
        verify(stravaUserRepository, times(1)).deleteById(1L);
    }

    private StravaUser getStravaUser() {
        StravaUser stravaUser = new StravaUser();
        stravaUser.setId(1L);
        stravaUser.setText("junitTest");
        return stravaUser;
    }

    private StravaUserResponse getStravaUserResponse() {
        return new StravaUserResponse(1L, "junitTest");
    }
}
