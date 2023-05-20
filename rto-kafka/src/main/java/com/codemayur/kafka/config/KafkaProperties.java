package com.codemayur.kafka.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ConfigurationProperties(prefix = "rto.kafka")
@PropertySources(value = {@PropertySource("classpath:kafka.properties")})
@Getter
@Setter
public class KafkaProperties {

    private String bootstrapAddress;
    private Long listenerPollTimeOut;
    private String autoOffsetResetConfig;
    private Integer maxPollRecord;

    private String bureauReportSyncTopic;
    private String bureauReportSyncGroup;
    private Integer bureauReportSyncConcurrency;

    private String generateRtoTopic;
    private String generateRtoGroup;
    private Integer generateRtoConcurrency;

}
