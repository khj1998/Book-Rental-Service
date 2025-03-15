package com.bookservice.framework.kafkaadapter;

import com.bookservice.domain.model.event.EventResult;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class BookEventProducer {
    @Value("${producers.topic1.name}")
    private String topic;
    private final KafkaTemplate<String, EventResult> kafkaTemplate;

    public void occurEvent(EventResult result) throws JsonProcessingException {
        log.info("Book Producing Event To Topic {}, Record {}","rental_result",result.toString());
        CompletableFuture<SendResult<String,EventResult>> future = this.kafkaTemplate.send(topic,result);

        future.thenAccept(sendResult -> {
            EventResult eventResult = sendResult.getProducerRecord().value();
            RecordMetadata metadata = sendResult.getRecordMetadata();
            log.info("send message=[{}] with offset=[{}] to topic=[{}]",eventResult.getEventType(),metadata.offset(),"rental_result");
        }).exceptionally(ex -> {
            log.error("unable to send message = [{}] due to : {}",result.getEventType(),ex.getMessage());
            return null;
        });
    }
}
