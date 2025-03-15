package com.rentalservice.framework.kafkaadapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentalservice.application.usecase.CompensationUseCase;
import com.rentalservice.domain.model.event.EventResult;
import com.rentalservice.domain.model.vo.EventType;
import com.rentalservice.domain.model.vo.IdName;
import com.rentalservice.domain.model.vo.Item;
import com.rentalservice.framework.web.exception.BasicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalEventConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CompensationUseCase compensationUseCase;

    @KafkaListener(topics = "${consumer.topic1.name}", groupId = "${consumer.groupid.name}")
    public void consumeRental(ConsumerRecord<String, String> record) throws Exception {
        log.info("Rental Book Consumer Consuming From Topic {}, Record {}","rental_result",record);
        EventResult eventResult = objectMapper.readValue(record.value(), EventResult.class);
        IdName idName = eventResult.getIdName();
        Item item = eventResult.getItem();
        long point = eventResult.getPoint();

        if (!eventResult.getIsSuccessed()) {
            EventType eventType = eventResult.getEventType();
            log.info("rental service 보상 트랜잭션 실행, 이벤트 : {}",eventType.toString());

            executeCompensation(eventType, idName, item, point);
        }
    }

    private void executeCompensation(EventType eventType, IdName idName, Item item, long point) throws Exception {
        switch (eventType) {
            case RENT:
                cancelRent(idName, item);
                break;
            case RETURN:
                cancelReturn(idName, item, point);
                break;
            case OVERDUE:
                cancelOverdue(idName, point);
                break;
            default:
                throw new BasicException("Unexpected EventType: " + eventType);
        }
    }

    private void cancelRent(IdName idName, Item item) {
        compensationUseCase.cancelRentItem(idName, item);
        log.info("대여취소 보상 트랜잭션 실행");
    }

    private void cancelReturn(IdName idName, Item item, long point) throws Exception {
        compensationUseCase.cancelReturnItem(idName, item, point);
        log.info("반납취소 보상 트랜잭션 실행");
    }

    private void cancelOverdue(IdName idName, long point) {
        compensationUseCase.cancelMakeAvailableRental(idName, point);
        log.info("연체 해제 처리 취소 보상 트랜잭션 실행");
    }
}