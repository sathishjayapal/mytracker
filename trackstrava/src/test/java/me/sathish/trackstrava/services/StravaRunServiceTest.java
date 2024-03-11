package me.sathish.trackstrava.services;

import org.junit.jupiter.api.extension.ExtendWith;
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
    //
    // given(stravaRunMapper.toResponse(any(StravaRun.class))).willReturn(getStravaRunResponse());
    //        // when
    //        Optional<StravaRunResponse> optionalStravaRun =
    // stravaRunService.findStravaRunById(1L);
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
