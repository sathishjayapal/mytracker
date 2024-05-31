package me.sathish.trackgarmin;

import me.sathish.trackgarmin.config.ApplicationProperties;
import me.sathish.trackgarmin.services.GarminFileParserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({ApplicationProperties.class})
public class GarminMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(GarminMSApplication.class, args);
    }
}

class RawGarminRunInitializer implements CommandLineRunner {
    private final GarminFileParserService garminFileParserService;

    public RawGarminRunInitializer(GarminFileParserService garminFileParserService) {
        this.garminFileParserService = garminFileParserService;
    }

    @Override
    public void run(String... args) throws Exception {
        garminFileParserService.readFirstLines();
    }
}
