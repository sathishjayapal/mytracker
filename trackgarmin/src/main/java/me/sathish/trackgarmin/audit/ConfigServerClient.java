package me.sathish.trackgarmin.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Base64;

@Component
public class ConfigServerClient {
    private static final Logger logger = LoggerFactory.getLogger(ConfigServerClient.class);
    RestClient restClient;

    public ConfigServerClient(RestClient.Builder builder, ServiceLogInterceptor serviceLogInterceptor) {
        this.restClient = builder.baseUrl("http://localhost:8888")
                .requestInterceptor(serviceLogInterceptor)
                .build();
    }

    public String getConfiguration(String appName, String profile) {
        logger.info("Fetching configuration for app: " + appName + " and profile: " + profile);
        return restClient.get().uri("/auth")
                .header("Authorization", "Basic "
                        + Base64.getEncoder().encodeToString(("sathish" + ":" + "pass")
                        .getBytes())).retrieve().body(new ParameterizedTypeReference<>() {
        });
    }
}
