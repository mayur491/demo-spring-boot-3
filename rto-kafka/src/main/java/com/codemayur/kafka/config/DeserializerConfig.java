package com.codemayur.kafka.config;

import com.codemayur.core.record.BureauReportRecord;
import com.codemayur.core.record.GenerateRtoRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@Slf4j
public class DeserializerConfig {

    @Bean("stringDeserializer")
    public Deserializer<String> stringDeserializer() {
        log.debug("Inside DeserializerConfig::stringDeserializer");
        return new StringDeserializer();
    }

    @Bean("bureauReportErrorHandlingJsonDeserializer")
    public Deserializer<BureauReportRecord> bureauReportErrorHandlingJsonDeserializer() {
        log.debug("Inside DeserializerConfig::bureauReportErrorHandlingJsonDeserializer");
        JsonDeserializer<BureauReportRecord> deserializer = new JsonDeserializer<>(BureauReportRecord.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        return new ErrorHandlingDeserializer<>(deserializer);
    }

    @Bean("generateRtoErrorHandlingJsonDeserializer")
    public Deserializer<GenerateRtoRecord> generateRtoErrorHandlingJsonDeserializer() {
        log.debug("Inside DeserializerConfig::generateRtoErrorHandlingJsonDeserializer");
        JsonDeserializer<GenerateRtoRecord> deserializer = new JsonDeserializer<>(GenerateRtoRecord.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        return new ErrorHandlingDeserializer<>(deserializer);
    }

}
