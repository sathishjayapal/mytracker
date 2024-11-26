package me.sathish.utils.garmindatainitializer;

import lombok.RequiredArgsConstructor;
import me.sathish.utils.garmindatainitializer.service.GarminFileParserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class FileLoadDataInitializer  implements CommandLineRunner {
    private final GarminFileParserService garminFileParserService;

    public void run(String... args) throws Exception {
        garminFileParserService.readFirstLines();
    }
}
