package me.sathish.garmindatainitializer.config;

import me.sathish.garmindatainitializer.ApplicationProperties;
import org.springframework.boot.http.client.ClientHttpRequestFactoryBuilder;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

@Configuration
public class EventServiceClientConfig {
    private final ApplicationProperties properties;

    public EventServiceClientConfig(ApplicationProperties properties) {
        this.properties = properties;
    }
    @Bean
    RestClientCustomizer restClientCustomizer() {
        return resetClientBuilder-> resetClientBuilder.baseUrl(properties.urn().baseUrl())
                .requestFactory(ClientHttpRequestFactories.get(ClientHttpRequestFactorySettings.DEFAULTS.
                        withConnectTimeout(Duration.ofSeconds(5)).withReadTimeout(Duration.ofSeconds(5))));
    }

    @Bean
    RestClient restClient(RestClient.Builder builder) {
        RestClient restClient= builder.build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.
                builderFor(RestClientAdapter.create(restClient))
                .build();
        return httpServiceProxyFactory.createClient(RestClient.class);
//        ClientHttpRequestFactory reqFactory = ClientHttpRequestFactoryBuilder.simple()
//                .withCustomizer(customeizer -> {
//                    customeizer.setConnectTimeout(properties.urn().connectTimeout());
//                    customeizer.setReadTimeout(properties.urn().readTimeout());//todo: check if this is correct
//                })
//                .build();
//        System.out.printf("EventServiceClientConfig: %s%n", properties.urn().baseUrl());
//        return builder.baseUrl(properties.urn().baseUrl())
//                .requestFactory(reqFactory)
//                .build();
    }
}
