package me.sathish.garmindatainitializer.retry.data;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class RetryCounter {
    private int retryCount = 0;

    public int incrementAndGet() {
        return ++retryCount;
    }
}
