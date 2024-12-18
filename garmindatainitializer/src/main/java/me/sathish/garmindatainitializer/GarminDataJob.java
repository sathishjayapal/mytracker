package me.sathish.garmindatainitializer;

import fr.ybonnel.csvengine.exception.CsvErrorsExceededException;
import me.sathish.garmindatainitializer.config.SchedulerSettings;
import me.sathish.garmindatainitializer.service.GarminFileParserService;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RefreshScope
public class GarminDataJob {
    private final GarminFileParserService garminFileParserService;
    private SchedulerSettings schedulerSettings;
    @Value("${spring.application.name}")
    private String appName;

    public GarminDataJob(GarminFileParserService garminFileParserService, SchedulerSettings schedulerSettings) {
        this.garminFileParserService = garminFileParserService;
        this.schedulerSettings = schedulerSettings;
    }

    @Scheduled(cron = "${scheduling.cron}")
    @SchedulerLock(name = "readFirstLines")
    void callFileParserService() throws CsvErrorsExceededException, InterruptedException, IOException {
        LockAssert.assertLocked();
        garminFileParserService.readFirstLines();
    }
}
