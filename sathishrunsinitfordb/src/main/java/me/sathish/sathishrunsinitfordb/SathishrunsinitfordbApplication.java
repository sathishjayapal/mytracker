package me.sathish.sathishrunsinitfordb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SathishrunsinitfordbApplication implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Database initialized");
    }

    public static void main(String[] args) {
        SpringApplication.run(SathishrunsinitfordbApplication.class, args);
    }
}
