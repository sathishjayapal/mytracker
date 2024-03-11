package me.sathish.trackgarmin.services;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class GarminRunProducer {
    public GarminRunProducer() {

    }
    public void send(){
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer kafkaProducer = new KafkaProducer<String, Integer>(properties);
        ProducerRecord producerRecord = new ProducerRecord("first-topic", "Sathish", "Sathish Message");
        try {
            kafkaProducer.send(producerRecord);
            System.out.printf("Message sent out correctly");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            kafkaProducer.close();
        }

    }
}
