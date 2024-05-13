package me.sathish.trackgarmin;

import lombok.RequiredArgsConstructor;
import me.sathish.trackgarmin.services.GarminFileParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RawGarminRunInitializer implements CommandLineRunner {
    @Autowired
    private GarminFileParserService garminFileParserService;

    @Override
    public void run(String... args) throws Exception {
        garminFileParserService.readFirstLines();
    }
}
