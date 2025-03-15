package com.rentalservice.application.inputport;

import com.rentalservice.application.outputport.RentalCardOutputPort;
import com.rentalservice.application.usecase.CreateRentalCardUseCase;
import com.rentalservice.domain.model.RentalCard;
import com.rentalservice.domain.model.vo.IdName;
import com.rentalservice.framework.web.dto.RentalCardOutputDto;
import com.rentalservice.framework.web.dto.UserInputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CreateRentalCardInputPort implements CreateRentalCardUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalCardOutputDto createRentalCard(UserInputDto owner) {
        log.info("Rental Card 생성, 받은 Dto : {}",owner.toString());
        RentalCard rentalCard = RentalCard.createRentalCard(new IdName(owner.getUserId(),owner.getUserName()));
        RentalCard save = rentalCardOutputPort.save(rentalCard);

        return RentalCardOutputDto.mapToDTO(save);
    }
}
