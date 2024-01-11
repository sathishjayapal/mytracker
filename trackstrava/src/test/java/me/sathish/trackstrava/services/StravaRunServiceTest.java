package me.sathish.trackstrava.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.willDoNothing;

import java.util.Optional;
import me.sathish.trackstrava.entities.StravaRun;
import me.sathish.trackstrava.mapper.StravaRunMapper;
import me.sathish.trackstrava.model.response.StravaRunResponse;
import me.sathish.trackstrava.repositories.StravaRunRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StravaRunServiceTest {
//
//    @Mock private StravaRunRepository stravaRunRepository;
//    @Mock private StravaRunMapper stravaRunMapper;
//
//    @InjectMocks private StravaRunService stravaRunService;
//
//    @Test
//    void findStravaRunById() {
//        // given
//        given(stravaRunRepository.findById(1L)).willReturn(Optional.of(getStravaRun()));
//        given(stravaRunMapper.toResponse(any(StravaRun.class))).willReturn(getStravaRunResponse());
//        // when
//        Optional<StravaRunResponse> optionalStravaRun = stravaRunService.findStravaRunById(1L);
//        // then
//        assertThat(optionalStravaRun).isPresent();
//        StravaRunResponse stravaRun = optionalStravaRun.get();
//        assertThat(stravaRun.id()).isEqualTo(1L);
//        assertThat(stravaRun.text()).isEqualTo("junitTest");
//    }
//
//    @Test
//    void deleteStravaRunById() {
//        // given
//        willDoNothing().given(stravaRunRepository).deleteById(1L);
//        // when
//        stravaRunService.deleteStravaRunById(1L);
//        // then
//        verify(stravaRunRepository, times(1)).deleteById(1L);
//    }
//
//    private StravaRun getStravaRun() {
//        StravaRun stravaRun = new StravaRun();
//        stravaRun.setId(1L);
//        stravaRun.setText("junitTest");
//        return stravaRun;
//    }
//
//    private StravaRunResponse getStravaRunResponse() {
//        return new StravaRunResponse(1L, "junitTest");
//    }
}
