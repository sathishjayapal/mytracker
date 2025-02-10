package me.sathish.streams.sathishdataconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SathishDataConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SathishDataConsumerApplication.class, args);
    }
    @Bean
    SathishKafkaConsumer sathishKafkaConsumer() {
        return new SathishKafkaConsumer();
    }

}
@Component
class SathishKafkaConsumer {

    @KafkaListener(topics = "test-wikimedia")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
