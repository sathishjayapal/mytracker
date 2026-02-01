package me.sathish.garmindatainitializer.service;

import fr.ybonnel.csvengine.CsvEngine;
import fr.ybonnel.csvengine.exception.CsvErrorsExceededException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import me.sathish.garmindatainitializer.data.DomainEventDTO;
import me.sathish.garmindatainitializer.data.FileNameTracker;
import me.sathish.garmindatainitializer.data.GarminRun;
import me.sathish.garmindatainitializer.data.RawActivities;
import me.sathish.garmindatainitializer.data.RawGarminRunMapper;
import me.sathish.garmindatainitializer.data.RunAppUser;
import me.sathish.garmindatainitializer.repos.FileNameTrackerRepository;
import me.sathish.garmindatainitializer.repos.GarminDataRepository;
import me.sathish.garmindatainitializer.repos.RunAppUserRepository;
import me.sathish.garmindatainitializer.retry.service.RetryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;

/**
 * Service class for parsing Garmin files and saving activities to the database.
 */
@Service
@Data
@RefreshScope
public class GarminFileParserService implements GarminEventService {
    private static final Logger logger = LoggerFactory.getLogger(GarminFileParserService.class);
    private final CsvEngine engine = new CsvEngine(RawActivities.class);
    private final GarminDataRepository garminRunRepository;
    private final FileNameTrackerRepository fileNameTrackerRepository;
    private final RunAppUserRepository runAppUserRepository;
    private final RawGarminRunMapper rawActivitiesMapper;
    private final RetryService retryService;
    private final Environment env;
    private final RestClient restClient;

    @Value("${team.value}")
    private String teamName;

    @Value("${team.backup}")
    private String backupTeamName;

    @Value("${location.url:NA}")
    private String urlName;

    @Value("${location.fileName}")
    private String fileName;

    @Value("${eventstracker.url:}")
    private String eventTrackerUrl;

    @Value("${eventstracker.username:}")
    private String eventTrackerUsername;

    @Value("${eventstracker.password:}")
    private String eventTrackerPassword;

    private String blobNameUrl;

    /**
     * Constructor for GarminFileParserService.
     *
     * @param garminRunRepository       the repository for GarminRun entities
     * @param rawActivitiesMapper       the mapper for converting RawActivities to GarminRun
     * @param fileNameTrackerRepository the repository for tracking file names
     * @param runAppUserRepository     the repository for RunAppUser entities
     * @param retryService              the service for handling retries
     * @param env                       the environment for accessing properties
     */
    public GarminFileParserService(
            GarminDataRepository garminRunRepository,
            RawGarminRunMapper rawActivitiesMapper,
            FileNameTrackerRepository fileNameTrackerRepository,
            RunAppUserRepository runAppUserRepository,
            RetryService retryService,
            Environment env,
            RestClient restClient) {
        this.garminRunRepository = garminRunRepository;
        this.rawActivitiesMapper = rawActivitiesMapper;
        this.fileNameTrackerRepository = fileNameTrackerRepository;
        this.runAppUserRepository = runAppUserRepository;
        this.retryService = retryService;
        this.env = env;
        this.blobNameUrl = env.getProperty("blobNameUrl");
        this.restClient = restClient;
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
     * @throws RuntimeException if there are any errors in saving the activities
     */
    @Transactional
    public void readFirstLines() {
        String output =
                StringUtils.hasText(blobNameUrl) ? blobNameUrl : StringUtils.hasText(urlName) ? urlName : fileName;
        var data = fileNameTrackerRepository.findByFileName(output);
        if (data.isPresent()) {
            recordRestClientEvent("ERROR- File already processed " + output, restClient);
            retryService.performShutDownTask();
        } else {
            try {
                List<RawActivities> rawActivitiesList = getRawActivities();
                recordRestClientEvent("SUCCESS- FILE READ", restClient);
                // Get or create the default system user for createdBy
                RunAppUser defaultUser = getOrCreateDefaultUser();
                List<GarminRun> activitiesList = garminRunRepository.saveAll(rawActivitiesList.stream()
                        .map(rawActivitiesMapper::toEntity)
                        .peek(garminRun -> garminRun.setCreatedBy(defaultUser))
                        .collect(Collectors.toList()));
                recordRestClientEvent("SUCCESS- FILE PROCESSING COMPLETED FILE/BLOB_NAME/" + output, restClient);
                logger.info("Saved the activities in the database: {}", activitiesList.size());
            } catch (Exception e) {
                recordRestClientEvent("ERROR- FILE PROCESSING/SAVING FILE/BLOB_NAME\t" + output, restClient);
                logger.error("Error in saving the activities: {}", e.getMessage());
                throw new RuntimeException("Error in saving the activities", e);
            }
        }
    }

    /**
     * Retrieves the raw activities from the CSV file or URL.
     *
     * @return the list of RawActivities
     * @throws IOException                if an I/O error occurs
     * @throws InterruptedException       if the thread is interrupted
     * @throws CsvErrorsExceededException if there are too many errors in the CSV file
     */
    @Transactional
    List<RawActivities> getRawActivities() throws IOException, InterruptedException, CsvErrorsExceededException {
        if ((blobNameUrl != null && blobNameUrl.startsWith("http"))
                || (urlName != null && urlName.startsWith("http"))) {
            HttpResponse<InputStream> response = HttpClient.newHttpClient()
                    .send(
                            HttpRequest.newBuilder()
                                    .uri(URI.create(StringUtils.hasText(blobNameUrl) ? blobNameUrl : urlName))
                                    .build(),
                            HttpResponse.BodyHandlers.ofInputStream());
            if (response.statusCode() != 200) {
                logger.error("Error in fetching the file from the url");
                retryService.performShutDownTask();
                throw new IOException("Error in fetching the file from the url");
            }
            updateUploadMetaDataDetails(StringUtils.hasText(blobNameUrl) ? blobNameUrl : urlName);
            return engine.parseFirstLinesOfInputStream(response.body(), RawActivities.class, 1000)
                    .getObjects();
        }
        updateUploadMetaDataDetails(fileName);
        return engine.parseFirstLinesOfInputStream(getCsvFile(), RawActivities.class, 1000)
                .getObjects();
    }

    /**
     * Gets or creates the default system user for audit fields.
     *
     * @return the default RunAppUser
     */
    private RunAppUser getOrCreateDefaultUser() {
        return runAppUserRepository.findByEmail("system@garmin.com").orElseGet(() -> {
            RunAppUser newUser = new RunAppUser();
            newUser.setEmail("system@garmin.com");
            newUser.setPassword("system");
            newUser.setName("System User");
            return runAppUserRepository.save(newUser);
        });
    }

    /**
     * Updates the file details in the EventTracker repository.
     *
     * @param fileName the name of the file
     */
    private void updateUploadMetaDataDetails(String fileName) {
        RunAppUser defaultUser = getOrCreateDefaultUser();
        FileNameTracker fileNameTracker = new FileNameTracker();
        fileNameTracker.setFileName(fileName);
        fileNameTracker.setCreatedBy(defaultUser);
        fileNameTrackerRepository.save(fileNameTracker);
    }

    @Override
    public void recordRestClientEvent(String eventType, RestClient restClient) {
        if (eventTrackerUrl == null || eventTrackerUrl.isEmpty()) {
            logger.info("Event tracker URL not configured, skipping event: {}", eventType);
            return;
        }
        try {
            String auth = eventTrackerUsername + ":" + eventTrackerPassword;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
            DomainEventDTO domainEventDTO = new DomainEventDTO();
            domainEventDTO.setEventId("GARMIN-" + System.currentTimeMillis());
            domainEventDTO.setEventType("GARMIN_EVENT");
            domainEventDTO.setPayload(eventType);
            domainEventDTO.setDomain(10002L);
            domainEventDTO.setCreatedBy("SYSTEM");
            domainEventDTO.setUpdatedBy("SYSTEM");
            restClient
                    .post()
                    .uri(eventTrackerUrl)
                    .body(domainEventDTO)
                    .header("Authorization", "Basic " + encodedAuth)
                    .header("Content-Type", "application/json")
                    .retrieve()
                    .toBodilessEntity();
            logger.info("Event recorded successfully: {}", eventType);
        } catch (Exception e) {
            logger.warn("Failed to record event: {} - {}", eventType, e.getMessage());
        }
    }
}
