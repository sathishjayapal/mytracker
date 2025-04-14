package me.sathish.query.sathishaccountquery.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionNameStrategy;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.rabbitmq.username:user}")
    private String username = "guest";

    @Value("${spring.rabbitmq.password:password}")
    private String password = "guest";

    @Value("${spring.rabbitmq.host:127.0.0.1}")
    private String hostname = "localhost";

    @Value("${spring.rabbitmq.port:5672}")
    private String port = "port";

    @Bean
    ConnectionNameStrategy connectionNameStrategy() {
        return (connectionFactory) -> applicationName;
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
