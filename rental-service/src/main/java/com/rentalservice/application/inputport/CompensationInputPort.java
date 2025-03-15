package com.rentalservice.application.inputport;

import com.rentalservice.application.outputport.EventOutputPort;
import com.rentalservice.application.outputport.RentalCardOutputPort;
import com.rentalservice.application.usecase.CompensationUseCase;
import com.rentalservice.domain.model.RentalCard;
import com.rentalservice.domain.model.event.PointUseCommand;
import com.rentalservice.domain.model.vo.IdName;
import com.rentalservice.domain.model.vo.Item;
import com.rentalservice.framework.web.exception.BasicException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CompensationInputPort implements CompensationUseCase {
    private final RentalCardOutputPort rentalCardOutputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public RentalCard cancelRentItem(IdName idName, Item item) {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(idName.getId())
                .orElseThrow(() -> new BasicException("rental card not found item number : "+item.getItemNo()));

        try {
            log.info("대여 취소 대상 Rental Card 정보 : {}",rentalCard.toString());
            rentalCard.cancelRentItem(item);
            eventOutputPort.occurPointUseCommand(new PointUseCommand(idName,10L));
        } catch (Exception e) {
            throw new BasicException(e.getMessage());
        }

        return rentalCard;
    }

    @Override
    public RentalCard cancelReturnItem(IdName idName, Item item, long point) throws Exception {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(idName.getId())
                .orElseThrow(() -> new BasicException("rental card not found item number : "+item.getItemNo()));

        try {
            log.info("대여 반납 취소 대상 Rental Card 정보 : {}",rentalCard.toString());
            rentalCard.cancelReturnItem(item);
            eventOutputPort.occurPointUseCommand(new PointUseCommand(idName,point));
        } catch (Exception e) {
            throw new BasicException(e.getMessage());
        }

        return rentalCard;
    }

    @Override
    public long cancelMakeAvailableRental(IdName idName, long point) {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(idName.getId())
                .orElseThrow(() -> new BasicException("rental card not found item number : "+idName.getId()));
        long canceledPoint;

        try {
            log.info("대여 가능한 상태 변환 취소 대상 Rental Card 정보 : {}",rentalCard.toString());
            canceledPoint = rentalCard.cancelMakeAvailableRental(point);
        } catch (Exception e) {
            throw new BasicException(e.getMessage());
        }

        return canceledPoint;
    }
}
