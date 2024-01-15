package me.sathish.trackgarmin;

import me.sathish.trackgarmin.common.ContainersConfig;
import org.springframework.boot.SpringApplication;

public class TestApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "local");
        SpringApplication.from(GarminMSApplication::main)
                .with(ContainersConfig.class)
                .run(args);
    }
}
