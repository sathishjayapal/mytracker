package me.sathish.common.sathishrunscommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SathishrunsCommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(SathishrunsCommonApplication.class, args);
    }
}
