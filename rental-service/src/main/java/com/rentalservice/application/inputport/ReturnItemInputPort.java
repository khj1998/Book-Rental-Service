package com.rentalservice.application.inputport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rentalservice.application.outputport.EventOutputPort;
import com.rentalservice.application.outputport.RentalCardOutputPort;
import com.rentalservice.application.usecase.ReturnItemUseCase;
import com.rentalservice.domain.model.RentalCard;
import com.rentalservice.domain.model.event.ItemReturnedEvent;
import com.rentalservice.domain.model.vo.Item;
import com.rentalservice.framework.web.dto.RentalCardOutputDto;
import com.rentalservice.framework.web.dto.UserItemInputDto;
import com.rentalservice.framework.web.exception.BasicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReturnItemInputPort implements ReturnItemUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public RentalCardOutputDto returnItem(UserItemInputDto returnDto) throws JsonProcessingException {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(returnDto.getUserId())
                .orElseThrow(() -> new BasicException(returnDto+" 해당 카드가 존재하지 않습니다."));

        log.info("user id : {}를 가지는 Rental Card의 반납 수행, 도서 정보 : [id : {}, title : {}]"
                ,returnDto.getUserId(),returnDto.getItemId(),returnDto.getItemTitle());

        Item returnedItem = new Item(returnDto.getItemId(), returnDto.getItemTitle());
        rentalCard.returnItem(returnedItem, LocalDate.now());

        ItemReturnedEvent itemReturned = RentalCard.createItemReturnedEvent(rentalCard.getMember(),returnedItem,10L);
        eventOutputPort.occurReturnEvent(itemReturned);

        RentalCard save = rentalCardOutputPort.save(rentalCard);
        return RentalCardOutputDto.mapToDTO(save);
    }
}
