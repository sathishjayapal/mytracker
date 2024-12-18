package me.sathish.garmindatainitializer.service;

import fr.ybonnel.csvengine.CsvEngine;
import fr.ybonnel.csvengine.exception.CsvErrorsExceededException;
import lombok.Data;
import me.sathish.garmindatainitializer.data.GarminRun;
import me.sathish.garmindatainitializer.data.RawActivities;
import me.sathish.garmindatainitializer.data.RawGarminRunMapper;
import me.sathish.garmindatainitializer.repos.GarminDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RefreshScope
public class GarminFileParserService {
    private static final Logger logger = LoggerFactory.getLogger(GarminFileParserService.class);
    final CsvEngine engine = new CsvEngine(RawActivities.class);
    final GarminDataRepository garminRunRepository;
    final RawGarminRunMapper rawActivitiesMapper;
    @Value("${location.url:NA}")
    String urlName;
    @Value("${location.fileName}")
    String fileName;
    public GarminFileParserService(GarminDataRepository garminRunRepository, RawGarminRunMapper rawActivitiesMapper) {
        this.garminRunRepository = garminRunRepository;
        this.rawActivitiesMapper = rawActivitiesMapper;
    }

    private InputStream getCsvFile() {
        System.out.println("**************\t" + this.getFileName());
        return GarminFileParserService.class.getResourceAsStream(this.getFileName());
    }

    /**
     * Reads the first lines of the activities1.csv file and saves them to the database.
     * If activities are already present in the database, it logs a message and returns.
     * Otherwise, it parses the first 1000 lines of the CSV file, maps them to GarminRun entities,
     * and saves them to the database.
     *
     * @throws CsvErrorsExceededException if there are too many errors while parsing the CSV file
     */

    @Transactional

    public void readFirstLines() throws CsvErrorsExceededException, InterruptedException, IOException {
        if (garminRunRepository.count() > 0) {
            logger.info("**************");
            logger.info("Activities already present in the database");
            logger.info("**************");
            return;
        } else {
            List<RawActivities> rawActivitiesList;
           logger.info("**************");
            logger.info("read the batch mode lines of activities1.csv");
            logger.info("**************");
            if (urlName.startsWith("http")) {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(this.getUrlName()))
                        .build();
                HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
                rawActivitiesList = engine.parseFirstLinesOfInputStream(
                                response.body(), RawActivities.class, 1000)
                        .getObjects();

            } else {
                rawActivitiesList = engine.parseFirstLinesOfInputStream(
                                getCsvFile(), RawActivities.class, 1000)
                        .getObjects();
            }
            Iterable<GarminRun> activitiesIterable = rawActivitiesList.stream()
                    .map(rawActivitiesMapper::toEntity)
                    .collect(Collectors.toList());
            List<GarminRun> activitiesList = garminRunRepository.saveAll(activitiesIterable);
            System.out.println("**************");
            System.out.println("Saved the activities in the database" + activitiesList.size());
            System.out.println("**************");
        }
    }
}
