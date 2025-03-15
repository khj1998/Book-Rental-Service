package com.userservice.framework.kafkaadapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.userservice.domain.event.EventResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberEventProducer {
    @Value("${producer.topic1.name}")
    private String topic;

    private final KafkaTemplate<String, EventResult> kafkaTemplate;

    public void occurEvent(EventResult eventResult) throws JsonProcessingException {
        log.info("Rental Book Producing Event To Topic {}, Record {}","rental_result",eventResult.toString());
        CompletableFuture<SendResult<String,EventResult>> future =
                this.kafkaTemplate.send(topic,eventResult);

        future.thenAccept(sendResult ->{
           EventResult eventSendResult = sendResult.getProducerRecord().value();
            RecordMetadata metadata = sendResult.getRecordMetadata();
            log.info("Sent message = [{}] with offset = [{}]",eventSendResult.getEventType(),
                    metadata.offset());
        }).exceptionally(ex -> {
            log.error("Unable to send message = [{}] due to : [{}]",eventResult.getEventType(),ex.getMessage());
            return null;
        });
    }
}
