package me.sathish.streams.sathishstravadataproducer.config;

import com.launchdarkly.eventsource.ConnectStrategy;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class GoveeDataProducer {
    static final Logger logger = org.slf4j.LoggerFactory.getLogger(GoveeDataProducer.class);
    private KafkaTemplate<String, String> kafkaTemplate;
    public GoveeDataProducer(SathishGoveeListener sathishGoveeListener, KafkaTemplate kafkaTemplate) throws InterruptedException {
        logger.info("Inside GoveeDataProducer");
        this.kafkaTemplate= kafkaTemplate;
        kafkaTemplate.send("sathish-garmin", "Hello, Govee! at" + LocalDateTime.now());
        BackgroundEventHandler eventHandler = new SathishGoveeListener(kafkaTemplate);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
//        String url = "mqtts://mqtt.openapi.govee.com:8883";
//        EventSource.Builder eventSourceBuilder = new EventSource.Builder(ConnectStrategy.
//                http(URI.create(url)).connectTimeout(5, TimeUnit.SECONDS));
//                http(URI.create("https://openapi.api.govee.com/router/api/v1/user/devices")).
//                header("Accept", "application/json").
//                header("Govee-API-Key", "a95fcdee-5a2f-483d-90cd-24d1f3a1a95c").connectTimeout(5, java.util.concurrent.TimeUnit.SECONDS));
//        BackgroundEventSource.Builder builder = new BackgroundEventSource.Builder(eventHandler, eventSourceBuilder);
//        BackgroundEventSource eventSource = builder.build();
//        eventSource.start();
//        TimeUnit.MINUTES.sleep(5);
        BackgroundEventSource bes = null;
        try {
            bes = new BackgroundEventSource.Builder(sathishGoveeListener,
                    new EventSource.Builder(
                            ConnectStrategy.http(new URI("https://openapi.api.govee.com/router/api/v1/user/devices")).header("Govee-API-Key", "a95fcdee-5a2f-483d-90cd-24d1f3a1a95c").
                                    connectTimeout(5, TimeUnit.SECONDS))).threadPriority(Thread.MAX_PRIORITY).build();
            bes.start();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}


