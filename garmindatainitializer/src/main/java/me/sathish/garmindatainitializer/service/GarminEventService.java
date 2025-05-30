package me.sathish.garmindatainitializer.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.random.RandomGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClient;

public interface GarminEventService {
    Logger logger = LoggerFactory.getLogger(GarminEventService.class);

    default void recordRestClientEvent(String eventType, RestClient restClient) {
        System.out.println("Event Type: " + eventType);
        String jsonPayload = String.format(
                "{\"eventId\": %d, \"type\": \"%s\", \"payload\": \"%s\", \"domainName\": \"%s\"}", // TODO externalize
                // this
                RandomGenerator.getDefault().nextInt(50, 1000), eventType, "Sample payload data", "GARMIN");
        String auth = "Admin:Admin1234%"; // replace with actual username and password
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        var dataReturn = restClient
                .post()
                .uri("http://localhost:9081/domain-events") // TODO externalize this
                .body(jsonPayload)
                .header("Authorization", "Basic " + encodedAuth)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(String.class);
        System.out.printf("Response: %s", dataReturn);
    }
}
