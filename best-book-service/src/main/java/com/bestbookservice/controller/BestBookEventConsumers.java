package com.bestbookservice.controller;

import com.bestbookservice.domain.event.ItemRentedEvent;
import com.bestbookservice.service.BestBookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class BestBookEventConsumers {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BestBookService bestBookService;

    @KafkaListener(topics="${consumer.topic1.name}",groupId = "${consumer.groudid.name}")
    public void consumeRentalEvent(ConsumerRecord<String, String> record) throws IOException {
        log.info("Best Book Consumer Consuming From Topic {}, Record {}","rental_rent",record);
        ItemRentedEvent itemRented = objectMapper.readValue(record.value(), ItemRentedEvent.class);
        bestBookService.dealBestBook(itemRented.getItem());
    }
}
