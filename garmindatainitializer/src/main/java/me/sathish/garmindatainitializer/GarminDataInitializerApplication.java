package me.sathish.garmindatainitializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class GarminDataInitializerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GarminDataInitializerApplication.class, args);
    }
}
