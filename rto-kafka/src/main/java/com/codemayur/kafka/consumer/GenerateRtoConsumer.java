package com.codemayur.kafka.consumer;


import com.codemayur.core.record.GenerateRtoRecord;
import com.codemayur.service.RTOConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class GenerateRtoConsumer {

    private final RTOConfigService rtoConfigService;

    public GenerateRtoConsumer(RTOConfigService rtoConfigService) {
        this.rtoConfigService = rtoConfigService;
    }

    @KafkaListener(topics = "${rto.kafka.generateRtoTopic}",
            groupId = "${rto.kafka.generateRtoGroup}",
            containerFactory = "stringGenerateRtoKafkaContainerFactory")
    @KafkaHandler
    public void consumeBureauReportSyncKafkaMessage(GenerateRtoRecord generateRtoRecord,
                                                    Acknowledgment ack) {
        try {
            consumeReport(generateRtoRecord);
        } catch (Exception e) {
            log.error("Exception in BureauKafkaConsumerListener::consumeBureauReportSyncKafkaMessage", e);
        } finally {
            ack.acknowledge();
        }
    }

    private void consumeReport(GenerateRtoRecord generateRtoRecord) {
        log.info(generateRtoRecord.toString());

        // DB Inserts
        Optional<String> configValue = rtoConfigService.getConfigValue("PRODUCT_TYPES");
        configValue.ifPresent(log::info);
    }

}
