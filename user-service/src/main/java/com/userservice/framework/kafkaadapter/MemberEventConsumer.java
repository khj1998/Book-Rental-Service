package com.userservice.framework.kafkaadapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userservice.application.usecase.SavePointUseCase;
import com.userservice.application.usecase.UsePointUseCase;
import com.userservice.domain.event.EventResult;
import com.userservice.domain.event.ItemOverdueClearedEvent;
import com.userservice.domain.event.ItemRentedEvent;
import com.userservice.domain.event.PointUseCommand;
import com.userservice.domain.vo.EventType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberEventConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SavePointUseCase savePointUseCase;
    private final UsePointUseCase usePointUseCase;
    private final MemberEventProducer memberEventProducer;

    @KafkaListener(topics="${consumer.topic1.name}",groupId = "$ {consumer.groupid.name}")
    public void consumeRent(ConsumerRecord<String, String> record) throws IOException {
        log.info("User Consumer Consuming From Topic {}, Record {}","rental_rent",record);
        ItemRentedEvent itemRented = objectMapper.readValue(record.value(), ItemRentedEvent.class);
        savePointUseCase.savePoint(itemRented.getIdName(),itemRented.getPoint());
    }

    @KafkaListener(topics="${consumer.topic2.name}",groupId = "${consumer.groupid.name}")
    public void consumeReturn(ConsumerRecord<String, String> record) throws IOException{
        log.info("User Consumer Consuming From Topic {}, Record {}","rental_return",record);
        ItemRentedEvent itemReturned = objectMapper.readValue(record.value(), ItemRentedEvent.class);
        savePointUseCase.savePoint(itemReturned.getIdName(),itemReturned.getPoint());
    }

    @KafkaListener(topics="${consumer.topic3.name}",groupId = "$ {consumer.groupid.name}")
    public void consumeClear(ConsumerRecord<String, String> record) throws Exception {
        log.info("User Consumer Consuming From Topic {}, Record {}","overdue_clear",record);

        ItemOverdueClearedEvent overdueCleared = objectMapper.readValue(record.value(), ItemOverdueClearedEvent.class);

        EventResult eventResult = new EventResult();
        eventResult.setEventType(EventType.OVERDUE);
        eventResult.setIdName(overdueCleared.getIdName());
        eventResult.setPoint(overdueCleared.getPoint());

        try {
            usePointUseCase.usePoint(overdueCleared.getIdName(),overdueCleared.getPoint());
            eventResult.setSuccessed(true);
        } catch (Exception e) {
            eventResult.setSuccessed(false);
        }

        memberEventProducer.occurEvent(eventResult);
    }

    @KafkaListener(topics = "${consumer.topic4.name}",groupId = "${consumer.groupid.name}")
    public void consumeUsePoint(ConsumerRecord<String,String> record) throws Exception {
        log.info("User Consumer Consuming From Topic {}, Record {}","point_use",record);

        PointUseCommand pointUseCommand = objectMapper.readValue(record.value(), PointUseCommand.class);

        usePointUseCase.usePoint(pointUseCommand.getIdName(),pointUseCommand.getPoint());
    }
}
