package me.sathish.garmindatainitializer.config;

import me.sathish.garmindatainitializer.ApplicationProperties;
import org.springframework.boot.http.client.ClientHttpRequestFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class EventServiceClientConfig {
    @Bean
    RestClient restClient(RestClient.Builder builder, ApplicationProperties properties) {
        ClientHttpRequestFactory reqFactory = ClientHttpRequestFactoryBuilder.simple()
                .withCustomizer(customeizer -> {
                    customeizer.setConnectTimeout(properties.urn().connectTimeout());
                    customeizer.setReadTimeout(properties.urn().readTimeout());
                })
                .build();
        System.out.printf("EventServiceClientConfig: %s%n", properties.urn().baseUrl());
        return builder.baseUrl(properties.urn().baseUrl())
                .requestFactory(reqFactory)
                .build();
    }
}
