package com.codemayur.kafka.consumer;

import com.codemayur.core.enums.ProductType;
import com.codemayur.kafka.producer.GenerateRtoProducer;
import com.codemayur.core.record.BureauReportRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class BureauReportConsumer {

    private static final List<ProductType> productTypes = Arrays.asList(ProductType.PL, ProductType.POSTPAID);

    private final GenerateRtoProducer generateRtoProducer;

    public BureauReportConsumer(GenerateRtoProducer generateRtoProducer) {
        this.generateRtoProducer = generateRtoProducer;
    }

    @KafkaListener(topics = "${rto.kafka.bureauReportSyncTopic}",
            groupId = "${rto.kafka.bureauReportSyncGroup}",
            containerFactory = "stringBureauReportSyncKafkaContainerFactory")
    @KafkaHandler
    public void consumeBureauReportSyncKafkaMessage(BureauReportRecord bureauReportRecord,
                                                    Acknowledgment ack) {
        try {
            consumeReport(bureauReportRecord);
        } catch (Exception e) {
            log.error("Exception in BureauKafkaConsumerListener::consumeBureauReportSyncKafkaMessage", e);
        } finally {
            ack.acknowledge();
        }
    }

    private void consumeReport(BureauReportRecord bureauReportRecord) {
        log.info(bureauReportRecord.toString());
        productTypes.parallelStream()
                .forEach(productType -> generateRtoProducer.produce(bureauReportRecord, productType));
    }

}
