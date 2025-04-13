package me.sathish.garmindatainitializer.retry.service;

import me.sathish.garmindatainitializer.retry.data.RetryCounter;
import me.sathish.garmindatainitializer.service.GarminEventService;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * Service class for handling retry logic.
 */
@Service
public class RetryService implements GarminEventService {
    private final RetryCounter retryCounter;
    private final ApplicationContext applicationContext;
    private final RestClient restClient;

    /**
     * Constructor for RetryService.
     *
     * @param retryCounter the counter for tracking retry attempts
     * @param applicationContext the application context for shutting down the application
     */
    public RetryService(
            RetryCounter retryCounter, ApplicationContext applicationContext, RestClient restClient) {
        this.retryCounter = retryCounter;
        this.applicationContext = applicationContext;
        this.restClient = restClient;
    }

    /**
     * Performs a task with retry logic. If the maximum number of retries is reached,
     * the application will shut down.
     */
    public void performShutDownTask() {
        int currentRetryCount = retryCounter.incrementAndGet();
        if (currentRetryCount >= 3) {
            recordRestClientEvent("ERROR- Max retries reached. Shutting down application...", restClient);
            // Send Email logic here.
            shutdownApplication();
        } else {
            recordRestClientEvent("Task succeeded!...", restClient);
        }
    }

    /**
     * Shuts down the application.
     */
    private void shutdownApplication()
    {
        recordRestClientEvent("Shutting down application...", restClient);
        System.exit(SpringApplication.exit(applicationContext, () -> 0));
    }

    public void performEmailTask() {
        // Send Email logic here.
        System.out.println("Email sent successfully");
    }
}
