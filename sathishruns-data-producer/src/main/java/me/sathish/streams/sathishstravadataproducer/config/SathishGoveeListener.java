package me.sathish.streams.sathishstravadataproducer.config;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SathishGoveeListener implements BackgroundEventHandler {
    static final Logger logger = org.slf4j.LoggerFactory.getLogger(SathishGoveeListener.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    public SathishGoveeListener(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void onOpen() {
        logger.info("Connection opened");
    }

    @Override
    public void onClosed() {
        logger.info("Connection closed");
    }

    @Override
    public void onMessage(String event, MessageEvent messageEvent) {
        logger.info("Event: " + event);
        logger.info("MessageEvent: " + messageEvent);
        String kafkaTopicName = "test-wikimedia";
        kafkaTemplate.send(new ProducerRecord<>(kafkaTopicName, messageEvent.getData())).whenComplete((recordMetadata, e) -> {
            if (e == null) {
                logger.debug("RecordMetadata: {}", recordMetadata);
                logger.info("RecordMetadata Topic: {}", recordMetadata.getRecordMetadata().topic());
                logger.info("RecordMetadata Partition: {}", recordMetadata.getRecordMetadata().partition());
                logger.info("RecordMetadata timestamp: {}", recordMetadata.getRecordMetadata().timestamp());
            } else {
                logger.error("Error: " + e);
            }
        });
    }

    @Override
    public void onComment(String comment) {
        logger.info("Comment: " + comment);
    }

    @Override
    public void onError(Throwable t) {
        logger.error("Error: " + t);
    }

}
