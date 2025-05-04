package me.sathish.trackgarmin.config;

import me.sathish.trackgarmin.audit.GarminMSAuditorImpl;
import me.sathish.trackgarmin.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class PersistenceConfiguration {
    @Bean
    public AuditorAware<User> auditorAware() {
        return new GarminMSAuditorImpl();
    }
}
