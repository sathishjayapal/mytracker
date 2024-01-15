package me.sathish.trackgarmin;

import me.sathish.trackgarmin.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class GarminMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(GarminMSApplication.class, args);
    }
}
