package me.sathish.trackgarmin;

import me.sathish.trackgarmin.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class GarminMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(GarminMSApplication.class, args);
    }
}
