package me.sathish.garmindatainitializer.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import fr.ybonnel.csvengine.CsvEngine;
import fr.ybonnel.csvengine.exception.CsvErrorsExceededException;
import java.io.IOException;
import java.util.List;
import me.sathish.garmindatainitializer.data.GarminRun;
import me.sathish.garmindatainitializer.data.RawActivities;
import me.sathish.garmindatainitializer.data.RawGarminRunMapper;
import me.sathish.garmindatainitializer.repos.FileNameTrackerRepository;
import me.sathish.garmindatainitializer.repos.GarminDataRepository;
import me.sathish.garmindatainitializer.retry.service.RetryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

@ExtendWith(MockitoExtension.class)
public class GarminFileParserServiceTest {

    @Mock
    private GarminDataRepository garminRunRepository;

    @Mock
    private FileNameTrackerRepository fileNameTrackerRepository;

    @Mock
    private RawGarminRunMapper rawActivitiesMapper;

    @Mock
    private RetryService retryService;

    @Mock
    private Environment env;

    @Mock
    private CsvEngine engine;

    @InjectMocks
    private GarminFileParserService garminFileParserService;

    @BeforeEach
    public void setUp() {
        when(env.getProperty("blobNameUrl")).thenReturn("http://example.com");
    }

    @Test
    public void testReadFirstLines_ActivitiesAlreadyPresent()
            throws CsvErrorsExceededException, InterruptedException, IOException {
        when(garminRunRepository.count()).thenReturn(1L);

        garminFileParserService.readFirstLines();

        verify(garminRunRepository, times(1)).count();
        verifyNoMoreInteractions(garminRunRepository);
    }

    @Test
    public void testReadFirstLines_NoActivitiesPresent()
            throws CsvErrorsExceededException, InterruptedException, IOException {
        when(garminRunRepository.count()).thenReturn(0L);
        //        when(engine.parseFirstLinesOfInputStream(any(InputStream.class), eq(RawActivities.class), eq(1000)))
        //                .thenReturn((Result<RawActivities>) List.of(new RawActivities()));
        when(rawActivitiesMapper.toEntity(any(RawActivities.class))).thenReturn(new GarminRun());
        when(garminRunRepository.saveAll(anyList())).thenReturn(List.of(new GarminRun()));
        garminFileParserService.readFirstLines();
        verify(garminRunRepository, times(1)).count();
        verify(garminRunRepository, times(1)).saveAll(anyList());
    }
}
