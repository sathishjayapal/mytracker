package me.sathish.garmindatainitializer.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.random.RandomGenerator;

import me.sathish.garmindatainitializer.data.DomainEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClient;

public interface GarminEventService {
    Logger logger = LoggerFactory.getLogger(GarminEventService.class);


//    domainEventDTO.setEventID(1L); // Assuming domain ID 1 for GARMIN


    default void recordRestClientEvent(String eventType, RestClient restClient) {
        System.out.println("Event Type: " + eventType);
        DomainEventDTO domainEventDTO = new DomainEventDTO();
        domainEventDTO.setEventId("A0001"); // Random event ID
        domainEventDTO.setEventType("GARMIN_EVENT"); // Assuming a constant event type for Garmin
        domainEventDTO.setPayload("Sample payload data"); // Sample payload data
        domainEventDTO.setDomain(Long.valueOf(10002));
        String auth = "sathish:sathish"; // replace with actual username and password
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        var dataReturn = restClient
                .post()
                .uri("http://localhost:9081/domainEvents/add") // TODO externalize this
                .body(domainEventDTO)
                .header("Authorization", "Basic " + encodedAuth)
                .header("Content-Type", "application/json")
                .retrieve();
        System.out.printf("Response: %s", dataReturn);
    }
}
