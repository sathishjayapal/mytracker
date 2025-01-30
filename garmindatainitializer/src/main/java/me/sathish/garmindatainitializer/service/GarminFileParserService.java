package me.sathish.garmindatainitializer.service;

import fr.ybonnel.csvengine.CsvEngine;
import fr.ybonnel.csvengine.exception.CsvErrorsExceededException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import me.sathish.garmindatainitializer.data.GarminRun;
import me.sathish.garmindatainitializer.data.RawActivities;
import me.sathish.garmindatainitializer.data.RawGarminRunMapper;
import me.sathish.garmindatainitializer.repos.GarminDataRepository;
import me.sathish.garmindatainitializer.retry.service.RetryService;
import me.sathish.garmindatainitializer.tracker.data.FileNameTracker;
import me.sathish.garmindatainitializer.tracker.repos.FileNameTrackerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for parsing Garmin files and saving activities to the database.
 */
@Service
@Data
@RefreshScope
public class GarminFileParserService {
    private static final Logger logger = LoggerFactory.getLogger(GarminFileParserService.class);
    private final CsvEngine engine = new CsvEngine(RawActivities.class);
    private final GarminDataRepository garminRunRepository;
    private final FileNameTrackerRepository fileNameTrackerRepository;
    private final RawGarminRunMapper rawActivitiesMapper;
    private final RetryService retryService;
    private final Environment env;

    @Value("${team.value}")
    private String teamName;

    @Value("${team.backup}")
    private String backupTeamName;

    @Value("${location.url:NA}")
    private String urlName;

    @Value("${location.fileName}")
    private String fileName;

    private String blobNameUrl;

    /**
     * Constructor for GarminFileParserService.
     *
     * @param garminRunRepository the repository for GarminRun entities
     * @param rawActivitiesMapper the mapper for converting RawActivities to GarminRun
     * @param fileNameTrackerRepository the repository for tracking file names
     * @param retryService the service for handling retries
     * @param env the environment for accessing properties
     */
    public GarminFileParserService(
            GarminDataRepository garminRunRepository,
            RawGarminRunMapper rawActivitiesMapper,
            FileNameTrackerRepository fileNameTrackerRepository,
            RetryService retryService,
            Environment env) {
        this.garminRunRepository = garminRunRepository;
        this.rawActivitiesMapper = rawActivitiesMapper;
        this.fileNameTrackerRepository = fileNameTrackerRepository;
        this.retryService = retryService;
        this.env = env;
        this.blobNameUrl = env.getProperty("blobNameUrl");
    }

    /**
     * Retrieves the CSV file as an InputStream.
     *
     * @return the InputStream of the CSV file
     */
    private InputStream getCsvFile() {
        return GarminFileParserService.class.getResourceAsStream(this.fileName);
    }

    /**
     * Reads the first lines of the CSV file and saves the activities to the database.
     *
     * @throws CsvErrorsExceededException if there are too many errors in the CSV file
     * @throws InterruptedException if the thread is interrupted
     * @throws IOException if an I/O error occurs
     */
    @Transactional
    public void readFirstLines() throws CsvErrorsExceededException, InterruptedException, IOException {
        if (garminRunRepository.count() > 0) {
            logger.info("Activities already present in the database");
            return;
        }
        List<RawActivities> rawActivitiesList = getRawActivities();
        List<GarminRun> activitiesList = garminRunRepository.saveAll(
                rawActivitiesList.stream().map(rawActivitiesMapper::toEntity).collect(Collectors.toList()));
        logger.info("Saved the activities in the database: {}", activitiesList.size());
    }

    /**
     * Retrieves the raw activities from the CSV file or URL.
     *
     * @return the list of RawActivities
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the thread is interrupted
     * @throws CsvErrorsExceededException if there are too many errors in the CSV file
     */
    List<RawActivities> getRawActivities() throws IOException, InterruptedException, CsvErrorsExceededException {
        if ((blobNameUrl != null && blobNameUrl.startsWith("http"))
                || (urlName != null && urlName.startsWith("http"))) {
            HttpResponse<InputStream> response = HttpClient.newHttpClient()
                    .send(
                            HttpRequest.newBuilder()
                                    .uri(URI.create(blobNameUrl != null ? blobNameUrl : urlName))
                                    .build(),
                            HttpResponse.BodyHandlers.ofInputStream());
            if (response.statusCode() != 200) {
                logger.error("Error in fetching the file from the url");
                retryService.performTask();
                throw new IOException("Error in fetching the file from the url");
            }
            updateFileDetails(fileName);
            return engine.parseFirstLinesOfInputStream(response.body(), RawActivities.class, 1000)
                    .getObjects();
        }
        updateFileDetails(fileName);
        return engine.parseFirstLinesOfInputStream(getCsvFile(), RawActivities.class, 1000)
                .getObjects();
    }

    /**
     * Updates the file details in the FileNameTracker repository.
     *
     * @param fileName the name of the file
     */
    private void updateFileDetails(String fileName) {
        FileNameTracker fileNameTracker = new FileNameTracker();
        fileNameTracker.setFilename(fileName);
        fileNameTrackerRepository.save(fileNameTracker);
    }
}
