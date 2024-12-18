package me.sathish.garmindatainitializer.config;

import me.sathish.garmindatainitializer.data.GarminMSDataInitAuditorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class PersistenceConfiguration {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new GarminMSDataInitAuditorImpl();
    }
}
