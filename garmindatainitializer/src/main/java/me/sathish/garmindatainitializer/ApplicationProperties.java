package me.sathish.garmindatainitializer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "app")
public record ApplicationProperties(CorsProperties cors, UrnProperties urn) {
    public record UrnProperties(
            String baseUrl,
            int connectTimeout,
            int readTimeout) {
    }

    public record CorsProperties(
            @DefaultValue("/**") String pathPattern,
            @DefaultValue("*") String allowedOrigins,
            @DefaultValue("*") String allowedMethods,
            @DefaultValue("*") String allowedHeaders) {
    }
}
