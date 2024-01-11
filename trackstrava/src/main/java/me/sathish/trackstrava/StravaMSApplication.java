package me.sathish.trackstrava;

import me.sathish.trackstrava.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class StravaMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(StravaMSApplication.class, args);
    }
}
