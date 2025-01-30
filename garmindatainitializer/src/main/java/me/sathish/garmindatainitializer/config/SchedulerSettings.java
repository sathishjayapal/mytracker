package me.sathish.garmindatainitializer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("scheduling")
public class SchedulerSettings {
    private String cron;
}
