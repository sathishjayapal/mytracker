package me.sathish.eventservice.config;

import me.sathish.eventservice.ApplicationProperties;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    private final ApplicationProperties applicationProperties;

    public FlywayConfig(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSourceUrl, dataSourceUsername, dataSourcePassword)
                .schemas(applicationProperties.db().defaultSchema())
                .createSchemas(true)
                .load();
        flyway.repair();
        flyway.migrate();
        flyway.validate();
        return flyway;
    }
}
