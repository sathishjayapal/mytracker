package me.sathish.trackgarmin.services;

import fr.ybonnel.csvengine.CsvEngine;
import fr.ybonnel.csvengine.exception.CsvErrorsExceededException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import me.sathish.entities.RawActivities;
import me.sathish.trackgarmin.entities.GarminRun;
import me.sathish.trackgarmin.mapper.RawGarminRunMapper;
import me.sathish.trackgarmin.repositories.GarminRunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class GarminFileParserService {
    private static final Logger logger = LoggerFactory.getLogger(GarminFileParserService.class);
    final CsvEngine engine = new CsvEngine(RawActivities.class);
    final GarminRunRepository garminRunRepository;
    final RawGarminRunMapper rawActivitiesMapper;

    private static InputStream getCsvFile() {
        return GarminFileParserService.class.getResourceAsStream("/activities.csv");
    }

    @Transactional
    public void readFirstLines() throws CsvErrorsExceededException {
        if (garminRunRepository.count() > 0) {
            logger.info("**************");
            logger.info("Activities already present in the database");
            logger.info("**************");
            return;
        } else {
            logger.info("**************");
            logger.info("read the batch mode lines of activities.csv");
            logger.info("**************");
            List<RawActivities> rawActivitiesList = engine.parseFirstLinesOfInputStream(
                            getCsvFile(), RawActivities.class, 1000)
                    .getObjects();
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
