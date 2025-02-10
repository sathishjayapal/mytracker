package me.sathish.streams.sathishstravadataproducer;

import me.sathish.streams.sathishstravadataproducer.config.ApplicationProperties;
import me.sathish.streams.sathishstravadataproducer.config.GoveeDataProducer;
import me.sathish.streams.sathishstravadataproducer.config.SathishGoveeListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class SathishStravaDataProducerApplication {
    private  SathishGoveeListener sathishGoveeListener;
    private  GoveeDataProducer goveeDataProducer;

    public SathishStravaDataProducerApplication(SathishGoveeListener sathishGoveeListener, GoveeDataProducer goveeDataProducer) {
        this.sathishGoveeListener=sathishGoveeListener;
        this.goveeDataProducer = goveeDataProducer;
    }

    public static void main(String[] args) {
        SpringApplication.run(SathishStravaDataProducerApplication.class, args);
    }
}

