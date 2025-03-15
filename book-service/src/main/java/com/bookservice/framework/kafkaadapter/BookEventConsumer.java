package com.bookservice.framework.kafkaadapter;

import com.bookservice.application.exception.BaseException;
import com.bookservice.application.usecase.MakeAvailableUseCase;
import com.bookservice.application.usecase.MakeUnavailableUseCase;
import com.bookservice.domain.model.event.EventResult;
import com.bookservice.domain.model.event.ItemRentedEvent;
import com.bookservice.domain.model.event.ItemReturnedEvent;
import com.bookservice.domain.model.vo.EventType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookEventConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final MakeAvailableUseCase makeAvailableUseCase;
    private final MakeUnavailableUseCase makeUnavailableUseCase;
    private final BookEventProducer bookEventProducer;

    @KafkaListener(topics = "${consumer.topic1.name}",groupId = "${consumer.groupid.name}")
    public void consumeRentalEvent(ConsumerRecord<String,String> record) throws JsonProcessingException {
        log.info("Book Consumer Consuming From Topic {}, Record {}","rental_rent",record);
        ItemRentedEvent itemRentedEvent = objectMapper.readValue(record.value(), ItemRentedEvent.class);

        EventResult eventResult = new EventResult();
        eventResult.setEventType(EventType.RENT);
        eventResult.setIdName(itemRentedEvent.getIdName());
        eventResult.setItem(itemRentedEvent.getItem());
        eventResult.setPoint(itemRentedEvent.getPoint());

        try {
            log.info("전송받은 값 : {}",record.value());
            makeUnavailableUseCase.makeUnavailable(itemRentedEvent.getItem().getItemNo());
            eventResult.setSuccessed(true);
        } catch (BaseException e) {
            log.error("BasicException has been occurred message : {}",e.getMessage());
            eventResult.setSuccessed(false);
        }

        bookEventProducer.occurEvent(eventResult);
    }

    @KafkaListener(topics = "${consumer.topic2.name}",groupId = "${consumer.groupid.name}")
    private void consumeReturnEvent(ConsumerRecord<String,String> record) throws JsonProcessingException {
        log.info("rental returned : {}",record.value());
        ItemReturnedEvent itemReturnedEvent = objectMapper.readValue(record.value(), ItemReturnedEvent.class);

        EventResult eventResult = new EventResult();
        eventResult.setEventType(EventType.RETURN);
        eventResult.setIdName(itemReturnedEvent.getIdName());
        eventResult.setItem(itemReturnedEvent.getItem());
        eventResult.setPoint(itemReturnedEvent.getPoint());

        try {
            log.info("전송받은 값 : {}",record.value());
            makeAvailableUseCase.makeAvailable(itemReturnedEvent.getItem().getItemNo());
            eventResult.setSuccessed(true);
        } catch (BaseException e) {
            log.error("BasicException has been occurred message : {}",e.getMessage());
            eventResult.setSuccessed(false);
        }

        bookEventProducer.occurEvent(eventResult);
    }
}
