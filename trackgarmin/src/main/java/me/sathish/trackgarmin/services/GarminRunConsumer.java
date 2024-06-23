package me.sathish.trackgarmin.services;

import java.util.Properties;

//@Component
public class GarminRunConsumer {
    public void recieve() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringSerializer");
        //        properties.setProperty("group.id", "OrderGroup");
//        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
//        try {
//            kafkaConsumer.subscribe(Collections.singletonList("first-topic"));
//            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(20));
//            for (ConsumerRecord record : consumerRecords) {
//                System.out.println("Message Key" + record.key());
//                System.out.println("Message VALUE" + record.value());
//            }
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        } finally {
//            kafkaConsumer.close();
//        }
    }
}
