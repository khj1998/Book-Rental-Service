package com.rentalservice.framework.kafkaadapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rentalservice.application.outputport.EventOutputPort;
import com.rentalservice.domain.model.event.ItemOverdueClearedEvent;
import com.rentalservice.domain.model.event.ItemRentedEvent;
import com.rentalservice.domain.model.event.ItemReturnedEvent;
import com.rentalservice.domain.model.event.PointUseCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class RentalKafkaProducer implements EventOutputPort {

    @Value("${producer.properties.topic1.name}")
    private String TOPIC_RENT;
    @Value("${producer.properties.topic2.name}")
    private String TOPIC_RETURN;
    @Value("${producer.properties.topic3.name}")
    private String TOPIC_CLEAR;
    @Value("${producer.properties.topic4.name}")
    private String TOPIC_POINT;

    private final KafkaTemplate<String, ItemRentedEvent> rentedEventKafkaTemplate;
    private final KafkaTemplate<String, ItemReturnedEvent> returnedEventKafkaTemplate;
    private final KafkaTemplate<String, ItemOverdueClearedEvent> overdueClearedEventKafkaTemplate;
    private final KafkaTemplate<String, PointUseCommand> pointUseCommandKafkaTemplate;

    @Override
    public void occurRentalEvent(ItemRentedEvent rentalItemEvent) throws JsonProcessingException {
        log.info("Rental Book Producing Event To Topic {}, Record {}","rental_rent",rentalItemEvent.toString());
        CompletableFuture<SendResult<String, ItemRentedEvent>> future = rentedEventKafkaTemplate.send(TOPIC_RENT,rentalItemEvent);

        future.thenAccept(result -> {
            ItemRentedEvent itemRentedEvent = result.getProducerRecord().value();
            log.info("Sent message=[" + itemRentedEvent.getItem().getItemNo() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
        }).exceptionally(ex -> {
            log.error("Unable to send message=[" + rentalItemEvent.getItem().getItemNo() + "] due to : " + ex.getMessage(), ex);
            return null;
        });

    }

    @Override
    public void occurReturnEvent(ItemReturnedEvent itemReturnedEvent) throws JsonProcessingException {
        log.info("Rental Book Producing Event To Topic {}, Record {}","rental_return",itemReturnedEvent.toString());
        CompletableFuture<SendResult<String, ItemReturnedEvent>> future = returnedEventKafkaTemplate.send(TOPIC_RETURN, itemReturnedEvent);

        future.thenAccept(result -> {
            ItemReturnedEvent itemReturnedEventSent = result.getProducerRecord().value();
            log.info("Sent message=[" + itemReturnedEventSent.getItem().getItemNo() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
        }).exceptionally(t -> {
            log.error("Unable to send message=[" + itemReturnedEvent.getItem().getItemNo() + "] due to : " + t.getMessage(), t);
            return null;
        });

    }

    @Override
    public void occurOverdueClearedEvent(ItemOverdueClearedEvent itemOverdueClearedEvent) throws JsonProcessingException {
        log.info("Rental Book Producing Event To Topic {}, Record {}","overdue_clear",itemOverdueClearedEvent.toString());
        CompletableFuture<SendResult<String,ItemOverdueClearedEvent>> future = overdueClearedEventKafkaTemplate.send(TOPIC_CLEAR, itemOverdueClearedEvent);

        future.thenAccept(result -> {
            ItemOverdueClearedEvent itemOverdueClearedEventSent = result.getProducerRecord().value();
            log.info("Sent message=[" + itemOverdueClearedEventSent.getIdName().getId() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
        }).exceptionally(t -> {
            log.error( "Unable to send message=[" +itemOverdueClearedEvent.getIdName().getId() + "] due to : " + t.getMessage(), t);
            return null;
        });
    }

    @Override
    public void occurPointUseCommand(PointUseCommand pointUseCommand) {
        log.info("Rental Book Producing Event To Topic {}, Record {}","point_use",pointUseCommand.toString());
        CompletableFuture<SendResult<String,PointUseCommand>> future = this.pointUseCommandKafkaTemplate.send(TOPIC_POINT,pointUseCommand);

        future.thenAccept(result -> {
            PointUseCommand pointCommand = result.getProducerRecord().value();
            log.info("Send message=[{}] with offset=[{}]",pointCommand.getIdName().getId(),
                    result.getRecordMetadata().offset());
        }).exceptionally(ex -> {
            log.error("Unable to send message =[{}] due to : {}",pointUseCommand.getIdName().getId(),
                    ex.getMessage());
            return null;
        });
    }
}
