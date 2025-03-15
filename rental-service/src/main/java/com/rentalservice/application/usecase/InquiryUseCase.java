package com.rentalservice.application.usecase;

import com.rentalservice.framework.web.dto.RentItemOutputDto;
import com.rentalservice.framework.web.dto.RentalCardOutputDto;
import com.rentalservice.framework.web.dto.ReturnItemOutputDto;

import java.util.List;
import java.util.Optional;

public interface InquiryUseCase {
    Optional<RentalCardOutputDto> getRentalCard(String userId);
    Optional<List<RentItemOutputDto>> getAllRentItem(String userId);
    Optional<List<ReturnItemOutputDto>> getAllReturnItem(String userId);
}
