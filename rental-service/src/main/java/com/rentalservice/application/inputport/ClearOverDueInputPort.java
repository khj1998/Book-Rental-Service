package com.rentalservice.application.inputport;

import com.rentalservice.application.outputport.EventOutputPort;
import com.rentalservice.application.outputport.RentalCardOutputPort;
import com.rentalservice.application.usecase.ClearOverDueItemUseCase;
import com.rentalservice.domain.model.RentalCard;
import com.rentalservice.domain.model.event.ItemOverdueClearedEvent;
import com.rentalservice.framework.web.dto.ClearOverDueInfoDto;
import com.rentalservice.framework.web.dto.RentalResultOutputDto;
import com.rentalservice.framework.web.exception.BasicException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClearOverDueInputPort implements ClearOverDueItemUseCase {

    private static final Logger log = LoggerFactory.getLogger(ClearOverDueInputPort.class);
    private final RentalCardOutputPort rentalCardOutputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public RentalResultOutputDto clearOverDue(ClearOverDueInfoDto clearOverDueInfoDto) throws Exception {
        log.info("clear over due 수행, 받은 dto : {}",clearOverDueInfoDto.toString());

        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(clearOverDueInfoDto.getUserId())
                .orElseThrow(() -> new BasicException("해당 카드가 존재하지 않습니다."));

        rentalCard.makeAvailableRentalByPoint(clearOverDueInfoDto.getPoint());
        RentalCard save = rentalCardOutputPort.save(rentalCard);

        ItemOverdueClearedEvent itemOverdueClearedEvent = RentalCard.createOverdueClearedEvent(rentalCard.getMember(), clearOverDueInfoDto.getPoint());
        eventOutputPort.occurOverdueClearedEvent(itemOverdueClearedEvent);

        return RentalResultOutputDto.mapToDTO(save);
    }
}
