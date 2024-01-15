package me.sathish.trackgarmin.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GarminRunServiceTest {
    //
    //    @Mock
    //    private GarminRunRepository garminRunRepository;
    //
    //    @Mock
    //    private GarminRunMapper garminRunMapper;
    //
    //    @InjectMocks
    //    private GarminRunService garminRunService;
    //
    //    @Test
    //    void findGarminRunById() {
    //        // given
    //        given(garminRunRepository.findById(1L)).willReturn(Optional.of(getGarminRun()));
    //        given(garminRunMapper.toResponse(any(GarminRun.class))).willReturn(getGarminRunResponse());
    //        // when
    //        Optional<GarminRunResponse> optionalGarminRun = garminRunService.findGarminRunById(1L);
    //        // then
    //        assertThat(optionalGarminRun).isPresent();
    //        GarminRunResponse garminRun = optionalGarminRun.get();
    //        assertThat(garminRun.id()).isEqualTo(1L);
    //        assertThat(garminRun.text()).isEqualTo("junitTest");
    //    }
    //
    //    @Test
    //    void deleteGarminRunById() {
    //        // given
    //        willDoNothing().given(garminRunRepository).deleteById(1L);
    //        // when
    //        garminRunService.deleteGarminRunById(1L);
    //        // then
    //        verify(garminRunRepository, times(1)).deleteById(1L);
    //    }
    //
    //    private GarminRun getGarminRun() {
    //        GarminRun garminRun = new GarminRun();
    //        garminRun.setId(1L);
    //        garminRun.setText("junitTest");
    //        return garminRun;
    //    }
    //
    //    private GarminRunResponse getGarminRunResponse() {
    //        return new GarminRunResponse(1L, "junitTest");
    //    }
}
