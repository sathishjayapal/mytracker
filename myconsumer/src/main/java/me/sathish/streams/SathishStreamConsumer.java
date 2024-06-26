package me.sathish.streams;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.Properties;

public class SathishStreamConsumer {

    static final Logger logger = org.slf4j.LoggerFactory.getLogger(SathishStreamConsumer.class);
    public void consumeDataFromKafka() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "http://localhost:19092");
        properties.setProperty("group.id", "test-sathish");
        properties.setProperty("auto.offset.reset", "earliest");
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.metrics();
        consumer.subscribe(Arrays.asList("test-sathish"));
        while (true) {
            for (var record : consumer.poll(1000)) {
                logger.info("Record: " + record);
                logger.info("Record Key: " + record.key()+"Record Value: " + record.value());
                logger.info("Partition: " + record.partition()+"Offset: " + record.offset());
            }
        }
    }
}

