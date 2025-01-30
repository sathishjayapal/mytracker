package me.sathish.garmindatainitializer.retry.service;

import me.sathish.garmindatainitializer.retry.data.RetryCounter;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Service class for handling retry logic.
 */
@Service
public class RetryService {
    private final RetryCounter retryCounter;
    private final ApplicationContext applicationContext;

    /**
     * Constructor for RetryService.
     *
     * @param retryCounter the counter for tracking retry attempts
     * @param applicationContext the application context for shutting down the application
     */
    public RetryService(RetryCounter retryCounter, ApplicationContext applicationContext) {
        this.retryCounter = retryCounter;
        this.applicationContext = applicationContext;
    }

    /**
     * Performs a task with retry logic. If the maximum number of retries is reached,
     * the application will shut down.
     */
    public void performTask() {
        int currentRetryCount = retryCounter.incrementAndGet();
        System.out.println("Retry attempt: " + currentRetryCount);
        if (currentRetryCount >= 3) {
            System.out.println("Max retries reached. Shutting down application...");
            shutdownApplication();
        } else {
            System.out.println("Task succeeded!");
        }
    }

    /**
     * Shuts down the application.
     */
    private void shutdownApplication() {
        System.exit(SpringApplication.exit(applicationContext, () -> 0));
    }
}
