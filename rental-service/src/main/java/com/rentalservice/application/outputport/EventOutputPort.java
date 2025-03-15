package com.rentalservice.application.outputport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rentalservice.domain.model.event.ItemOverdueClearedEvent;
import com.rentalservice.domain.model.event.ItemRentedEvent;
import com.rentalservice.domain.model.event.ItemReturnedEvent;
import com.rentalservice.domain.model.event.PointUseCommand;

public interface EventOutputPort {
    void occurRentalEvent(ItemRentedEvent rentalItemEvent) throws JsonProcessingException;
    void occurReturnEvent(ItemReturnedEvent itemReturnedEvent) throws JsonProcessingException;
    void occurOverdueClearedEvent(ItemOverdueClearedEvent itemOverdueClearedEvent) throws JsonProcessingException;
    void occurPointUseCommand(PointUseCommand pointUseCommand);
}
