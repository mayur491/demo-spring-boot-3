package com.codemayur.kafka.config;

import com.codemayur.core.record.BureauReportRecord;
import com.codemayur.core.record.GenerateRtoRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

@Configuration
@Slf4j
public class KafkaContainerFactoryConfig {

    private final KafkaProperties kafkaProperties;
    private final ConsumerFactory<String, BureauReportRecord> stringBureauReportSyncKafkaConsumerFactory;
    private final ConsumerFactory<String, GenerateRtoRecord> stringGenerateRtoKafkaConsumerFactory;

    public KafkaContainerFactoryConfig(KafkaProperties kafkaProperties,
                                       @Qualifier("stringBureauReportSyncKafkaConsumerFactory") ConsumerFactory<String, BureauReportRecord> stringBureauReportSyncKafkaConsumerFactory,
                                       @Qualifier("stringGenerateRtoKafkaConsumerFactory") ConsumerFactory<String, GenerateRtoRecord> stringGenerateRtoKafkaConsumerFactory) {
        this.kafkaProperties = kafkaProperties;
        this.stringBureauReportSyncKafkaConsumerFactory = stringBureauReportSyncKafkaConsumerFactory;
        this.stringGenerateRtoKafkaConsumerFactory = stringGenerateRtoKafkaConsumerFactory;
    }

    @Bean("stringBureauReportSyncKafkaContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, BureauReportRecord> stringBureauReportSyncKafkaContainerFactory() {
        log.debug("Inside KafkaContainerFactoryConfig::stringBureauReportSyncKafkaContainerFactory");
        ConcurrentKafkaListenerContainerFactory<String, BureauReportRecord> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stringBureauReportSyncKafkaConsumerFactory);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        factory.getContainerProperties().setPollTimeout(kafkaProperties.getListenerPollTimeOut());
        factory.setConcurrency(kafkaProperties.getBureauReportSyncConcurrency());
        return factory;
    }

    @Bean("stringGenerateRtoKafkaContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, GenerateRtoRecord> stringGenerateRtoKafkaContainerFactory() {
        log.debug("Inside KafkaContainerFactoryConfig::stringGenerateRtoKafkaContainerFactory");
        ConcurrentKafkaListenerContainerFactory<String, GenerateRtoRecord> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stringGenerateRtoKafkaConsumerFactory);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        factory.getContainerProperties().setPollTimeout(kafkaProperties.getListenerPollTimeOut());
        factory.setConcurrency(kafkaProperties.getGenerateRtoConcurrency());
        return factory;
    }

}
