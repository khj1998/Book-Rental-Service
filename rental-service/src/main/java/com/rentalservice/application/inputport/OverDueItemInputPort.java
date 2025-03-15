package com.rentalservice.application.inputport;

import com.rentalservice.application.outputport.RentalCardOutputPort;
import com.rentalservice.application.usecase.OverDueItemUseCase;
import com.rentalservice.domain.model.RentalCard;
import com.rentalservice.domain.model.vo.Item;
import com.rentalservice.framework.web.dto.RentalCardOutputDto;
import com.rentalservice.framework.web.dto.UserItemInputDto;
import com.rentalservice.framework.web.exception.BasicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OverDueItemInputPort implements OverDueItemUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalCardOutputDto overDueItem(UserItemInputDto rental) {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(rental.getUserId())
                .orElseThrow(() -> new BasicException("해당 카드가 존재하지 않습니다."));

        log.info("user id {} 값을 가지는 Rental Card의 반납 기간 초과 처리, 초과 대상 도서 정보 : [id : {},title : {}]",rental.getUserId(),rental.getItemId(),rental.getItemTitle());
        rentalCard.overdueItem(new Item(rental.getItemId(),rental.getItemTitle()));
        return RentalCardOutputDto.mapToDTO(rentalCard);
    }
}
