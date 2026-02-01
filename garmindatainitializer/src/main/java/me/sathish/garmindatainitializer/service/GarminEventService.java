package me.sathish.garmindatainitializer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClient;

public interface GarminEventService {
    Logger logger = LoggerFactory.getLogger(GarminEventService.class);

    void recordRestClientEvent(String eventType, RestClient restClient);
}
