package com.codemayur.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@Slf4j
public class KafkaProducerConfig {

    private final ProducerFactory<String, Object> producerFactory;

    public KafkaProducerConfig(@Qualifier("stringJsonKafkaProducerFactory") ProducerFactory<String, Object> producerFactory) {
        this.producerFactory = producerFactory;
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        log.debug("Inside KafkaProducerConfig::kafkaTemplate");
        return new KafkaTemplate<>(producerFactory);
    }

}
