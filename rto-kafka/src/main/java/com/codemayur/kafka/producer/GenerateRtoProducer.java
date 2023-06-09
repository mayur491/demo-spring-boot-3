package com.codemayur.kafka.producer;

import com.codemayur.core.enums.ProductType;
import com.codemayur.core.record.BureauReportRecord;
import com.codemayur.core.record.GenerateRtoRecord;
import com.codemayur.kafka.config.KafkaProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class GenerateRtoProducer {

    private final KafkaProperties kafkaProperties;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public GenerateRtoProducer(KafkaProperties kafkaProperties, KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaProperties = kafkaProperties;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(BureauReportRecord bureauReportRecord, ProductType productType) {
        GenerateRtoRecord generateRtoRecord = GenerateRtoRecord.builder()
                .bureauReportRecord(bureauReportRecord)
                .productType(productType)
                .build();
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(kafkaProperties.getGenerateRtoTopic(),
                bureauReportRecord.customerId(),
                generateRtoRecord);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message with offset=[{}]", result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send message due to: {}", ex.getMessage());
            }
        });
    }

}
