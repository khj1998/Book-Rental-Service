package com.rentalservice.application.usecase;

import com.rentalservice.framework.web.dto.RentalCardOutputDto;
import com.rentalservice.framework.web.dto.UserItemInputDto;

//연체 usecase
public interface OverDueItemUseCase {
    RentalCardOutputDto overDueItem(UserItemInputDto rental) throws Exception;
}
