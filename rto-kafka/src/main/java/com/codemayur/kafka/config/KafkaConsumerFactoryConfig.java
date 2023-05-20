package com.codemayur.kafka.config;

import com.codemayur.core.record.BureauReportRecord;
import com.codemayur.core.record.GenerateRtoRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class KafkaConsumerFactoryConfig {

    private final KafkaProperties kafkaProperties;

    private final Deserializer<String> stringDeserializer;

    private final Deserializer<BureauReportRecord> bureauReportDeserializer;

    private final Deserializer<GenerateRtoRecord> generateRtoDeserializer;

    public KafkaConsumerFactoryConfig(KafkaProperties kafkaProperties,
                                      @Qualifier("stringDeserializer") Deserializer<String> stringDeserializer,
                                      @Qualifier("bureauReportErrorHandlingJsonDeserializer") Deserializer<BureauReportRecord> bureauReportDeserializer,
                                      @Qualifier("generateRtoErrorHandlingJsonDeserializer") Deserializer<GenerateRtoRecord> generateRtoDeserializer) {
        this.kafkaProperties = kafkaProperties;
        this.stringDeserializer = stringDeserializer;
        this.bureauReportDeserializer = bureauReportDeserializer;
        this.generateRtoDeserializer = generateRtoDeserializer;
    }

    @Bean("stringBureauReportSyncKafkaConsumerFactory")
    public ConsumerFactory<String, BureauReportRecord> stringBureauReportSyncKafkaConsumerFactory() {
        log.debug("Inside KafkaConsumerFactoryConfig::stringBureauReportSyncKafkaConsumerFactory");
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapAddress());
        config.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getBureauReportSyncGroup());
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, stringDeserializer);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, bureauReportDeserializer);
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, kafkaProperties.getMaxPollRecord());
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getAutoOffsetResetConfig());
        return new DefaultKafkaConsumerFactory<>(config, stringDeserializer, bureauReportDeserializer);
    }

    @Bean("stringGenerateRtoKafkaConsumerFactory")
    public ConsumerFactory<String, GenerateRtoRecord> stringGenerateRtoKafkaConsumerFactory() {
        log.debug("Inside KafkaConsumerFactoryConfig::stringGenerateRtoKafkaConsumerFactory");
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapAddress());
        config.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getGenerateRtoGroup());
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, stringDeserializer);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, generateRtoDeserializer);
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, kafkaProperties.getMaxPollRecord());
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getAutoOffsetResetConfig());
        return new DefaultKafkaConsumerFactory<>(config, stringDeserializer, generateRtoDeserializer);
    }

}
