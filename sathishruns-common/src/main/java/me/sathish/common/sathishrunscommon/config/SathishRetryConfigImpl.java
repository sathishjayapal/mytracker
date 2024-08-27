package me.sathish.common.sathishrunscommon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class SathishRetryConfigImpl {
    private SathishRetryConfigParams sathishRetryConfigParams;

    public SathishRetryConfigImpl(SathishRetryConfigParams configData) {
        this.sathishRetryConfigParams = configData;
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(sathishRetryConfigParams.getInitialIntervalMs());
        exponentialBackOffPolicy.setMaxInterval(sathishRetryConfigParams.getMaxIntervalMs());
        exponentialBackOffPolicy.setMultiplier(sathishRetryConfigParams.getMultiplier());

        retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(sathishRetryConfigParams.getMaxAttempts());

        retryTemplate.setRetryPolicy(simpleRetryPolicy);

        return retryTemplate;
    }
}
