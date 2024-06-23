package me.sathish.streams.sathishrunsdataproducer;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Properties;
import java.util.random.RandomGenerator;

@SpringBootApplication
public class SathishrunsDataproducerApplication {
    static final Logger logger = org.slf4j.LoggerFactory.getLogger(SathishrunsDataproducerApplication.class);

    public static void main(String[] args) {
        logger.info("Starting SathishrunsDataproducerApplication");
        SpringApplication.run(SathishrunsDataproducerApplication.class, args);
        logger.info("Ended SathishrunsDataproducerApplication");
    }

    @Bean
    public DataProducer produceData() {
        logger.info("Insider Producing data");
        return new DataProducer();
    }

    @Bean
    public DataConsumer consumerData() {
        logger.info("Insider Consumer data");
        return new DataConsumer();
    }
}

@Component
class DataProducer {
    static final Logger logger = org.slf4j.LoggerFactory.getLogger(DataProducer.class);

    public DataProducer() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "http://localhost:19092");
        properties.setProperty("acks", "all");
        properties.setProperty("Timeout.ms", "50000");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        producer.metrics();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 30; i++) {
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>("test-sathish", "sathish" + RandomGenerator.getDefault().nextInt(), "value" + RandomGenerator.getDefault().nextLong());
                producer.send(producerRecord, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if (e == null) {
                            logger.info("RecordMetadata: " + recordMetadata);
                            logger.info("RecordMetadata Topic: " + recordMetadata.topic());
                            logger.info("RecordMetadata Partition: " + recordMetadata.partition());
                            logger.info("RecordMetadata Partition: " + recordMetadata.timestamp());
                        } else {
                            logger.error("Error: " + e);
                        }
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        producer.flush();
        producer.close();
        logger.info("DataProducer created");
    }

}

@Component
class DataConsumer {
    static final Logger logger = org.slf4j.LoggerFactory.getLogger(DataProducer.class);

    public DataConsumer() {
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
                logger.info("Record Key: " + record.key());
                logger.info("Record Value: " + record.value());
            }
        }
    }
}
