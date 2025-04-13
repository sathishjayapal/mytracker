package me.sathish.garmindatainitializer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.random.RandomGenerator;

public interface GarminEventService {
    Logger logger = LoggerFactory.getLogger(GarminEventService.class);

    default void recordRestClientEvent(String eventType, RestClient restClient) {
        String jsonPayload = String.format(
                "{\"eventId\": %d, \"type\": \"%s\", \"payload\": \"%s\", \"domainName\": \"%s\"}",
                RandomGenerator.getDefault().nextInt(50,1000), eventType, "Sample payload data", "GARMIN");
        String auth = "Admin:Admin1234%"; // replace with actual username and password
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        var dataReturn= restClient
                .post()
                .uri("http://localhost:8082/domain-events")
                .body(jsonPayload)
                .header("Authorization", "Basic " + encodedAuth)
                .header("Content-Type", "application/json").retrieve().body(String.class);
        System.out.printf("Response: %s", dataReturn);
    }

}
