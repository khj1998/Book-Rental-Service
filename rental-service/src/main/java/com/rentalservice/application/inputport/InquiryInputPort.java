package com.rentalservice.application.inputport;

import com.rentalservice.application.outputport.RentalCardOutputPort;
import com.rentalservice.application.usecase.InquiryUseCase;
import com.rentalservice.framework.web.dto.RentItemOutputDto;
import com.rentalservice.framework.web.dto.RentalCardOutputDto;
import com.rentalservice.framework.web.dto.ReturnItemOutputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class InquiryInputPort implements InquiryUseCase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public Optional<RentalCardOutputDto> getRentalCard(String userId) {
        log.info("rental card id {} 값으로 조회",userId);
        return rentalCardOutputPort.loadRentalCard(userId)
                .map(RentalCardOutputDto::mapToDTO);
    }

    @Override
    public Optional<List<RentItemOutputDto>> getAllRentItem(String userId) {
        log.info("id {} 값을 가지는 rental card의 모든 대여 도서 조회",userId);
        return rentalCardOutputPort.loadRentalCard(userId)
                .map(loadCard -> loadCard.getRentalItemList().stream()
                        .map(RentItemOutputDto::mapToDTO)
                        .collect(Collectors.toList()));
    }

    @Override
    public Optional<List<ReturnItemOutputDto>> getAllReturnItem(String userId) {
        log.info("id {} 값을 가지는 rental card의 모든 반납 도서 조회",userId);
        return rentalCardOutputPort.loadRentalCard(userId)
                .map(loadCard -> loadCard.getReturnItemList().stream()
                        .map(ReturnItemOutputDto::mapToDTO)
                        .collect(Collectors.toList()));
    }
}
