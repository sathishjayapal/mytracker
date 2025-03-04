package me.sathish.garmindatainitializer.service;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;

public interface GarminEventService {
    Logger logger = LoggerFactory.getLogger(GarminEventService.class);

    default void recordEvent(String eventType, DiscoveryClient discoveryClient) {
        discoveryClient.getInstances("EVENTSTRACKER").forEach(serviceInstance -> {
            GarminEventService.logger.info("Service Instance: {}", serviceInstance.getUri());
            String jsonPayload = String.format(
                    "{\"eventId\": %d, \"type\": \"%s\", \"payload\": \"%s\", \"domainName\": \"%s\"}",
                    RandomUtils.nextInt(10), eventType, "Sample payload data", "GARMIN");
            String auth = "Admin:Admin1234%"; // replace with actual username and password
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(serviceInstance.getUri().resolve("/domain-events"))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .header("Authorization", "Basic " + encodedAuth)
                    .header("Content-Type", "application/json")
                    .build();
            try {

                HttpResponse<String> response =
                        HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                GarminEventService.logger.info("Response: {}", response.body());
            } catch (IOException | InterruptedException e) {
                GarminEventService.logger.error("Error in sending request: {}", e.getMessage());
            }
        });
    }
}
