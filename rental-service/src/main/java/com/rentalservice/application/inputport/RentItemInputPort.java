package com.rentalservice.application.inputport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rentalservice.application.outputport.EventOutputPort;
import com.rentalservice.application.outputport.RentalCardOutputPort;
import com.rentalservice.application.usecase.RentItemUseCase;
import com.rentalservice.domain.model.RentalCard;
import com.rentalservice.domain.model.event.ItemRentedEvent;
import com.rentalservice.domain.model.vo.IdName;
import com.rentalservice.domain.model.vo.Item;
import com.rentalservice.framework.web.dto.RentalCardOutputDto;
import com.rentalservice.framework.web.dto.UserItemInputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RentItemInputPort implements RentItemUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public RentalCardOutputDto rentItem(UserItemInputDto rental) throws JsonProcessingException {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(rental.getUserId())
                .orElseGet(() -> RentalCard.createRentalCard(new IdName(rental.getUserId(),rental.getUserName())));

        log.info("user id : {}를 가지는 Rental Card의 대여 수행, 도서 정보 : [id : {}, title : {}]",rental.getUserId(),rental.getItemId(),rental.getItemTitle());

        Item newItem = new Item(rental.getItemId(),rental.getItemTitle());
        rentalCard.rentItem(newItem);

        ItemRentedEvent itemRentedEvent = RentalCard.createItemRentedEvent(rentalCard.getMember(),newItem,10L);
        eventOutputPort.occurRentalEvent(itemRentedEvent);

        RentalCard save = rentalCardOutputPort.save(rentalCard);
        return RentalCardOutputDto.mapToDTO(save);
    }
}
