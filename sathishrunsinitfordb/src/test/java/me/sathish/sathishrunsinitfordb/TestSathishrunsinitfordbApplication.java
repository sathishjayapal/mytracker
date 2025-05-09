package me.sathish.sathishrunsinitfordb;

import org.springframework.boot.SpringApplication;

public class TestSathishrunsinitfordbApplication {

    public static void main(String[] args) {
        SpringApplication.from(SathishrunsinitfordbApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
