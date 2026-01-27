package me.sathish.garmindatainitializer.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import me.sathish.garmindatainitializer.data.DomainEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

public interface GarminEventService {
    Logger logger = LoggerFactory.getLogger(GarminEventService.class);
    void recordRestClientEvent(String eventType, RestClient restClient);

}
