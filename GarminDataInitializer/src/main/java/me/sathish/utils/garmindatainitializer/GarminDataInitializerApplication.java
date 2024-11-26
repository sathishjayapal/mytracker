package me.sathish.utils.garmindatainitializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class GarminDataInitializerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GarminDataInitializerApplication.class, args);
    }
}
