package me.sathish.garmindatainitializer.service;

import me.sathish.garmindatainitializer.data.DomainEventDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class GarminEventServiceImpl implements GarminEventService {
    //    domainEventDTO.setEventID(1L); // Assuming domain ID 1 for GARMIN
    @Value("${eventstracker.password}")
    private String password;

    @Value("${eventstracker.username}")
    private String username;

     public void recordRestClientEvent(String eventType, RestClient restClient) {
        System.out.println("Event Type: " + eventType);
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encodedAuth);
        DomainEventDTO domainEventDTO = new DomainEventDTO();
        domainEventDTO.setEventId("A0001"); // Random event ID
        domainEventDTO.setEventType("GARMIN_EVENT"); // Assuming a constant event type for Garmin
        domainEventDTO.setPayload("Sample payload data"); // Sample payload data
        domainEventDTO.setDomain(Long.valueOf(10002));
        domainEventDTO.setCreatedBy("SYSTEM");
        domainEventDTO.setUpdatedBy("SYSTEM");
        HttpEntity<DomainEventDTO> request = new HttpEntity<>(domainEventDTO, headers);
        RestTemplate restTemplate = new RestTemplate();
        String strData = restTemplate.postForObject("http://localhost:9081/api/domainEvents", request, String.class);
        var dataReturn = restClient
                .post()
                .uri("http://localhost:9081/api/domainEvents") // TODO externalize this
                .body(domainEventDTO)
                .header("Authorization", "Basic " + encodedAuth)
                .header("Content-Type", "application/json")
                .retrieve();
        System.out.printf("Response: %s", dataReturn);
    }
}
