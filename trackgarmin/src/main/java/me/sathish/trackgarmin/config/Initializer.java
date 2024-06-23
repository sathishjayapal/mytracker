package me.sathish.trackgarmin.config;

import fr.ybonnel.csvengine.exception.CsvErrorsExceededException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sathish.trackgarmin.services.GarminFileParserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Initializer implements CommandLineRunner {

    private final ApplicationProperties properties;
    private final GarminFileParserService garminFileParserService;
    @Override
    public void run(String... args) throws CsvErrorsExceededException {
        log.info("Running Initializer.....");
        garminFileParserService.readFirstLines();
    }
}
