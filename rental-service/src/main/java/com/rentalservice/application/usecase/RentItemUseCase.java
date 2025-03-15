package com.rentalservice.application.usecase;

import com.rentalservice.framework.web.dto.RentalCardOutputDto;
import com.rentalservice.framework.web.dto.UserItemInputDto;

public interface RentItemUseCase {
    RentalCardOutputDto rentItem(UserItemInputDto rental) throws Exception;
}
