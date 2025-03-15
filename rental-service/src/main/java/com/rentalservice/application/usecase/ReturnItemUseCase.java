package com.rentalservice.application.usecase;


import com.rentalservice.framework.web.dto.RentalCardOutputDto;
import com.rentalservice.framework.web.dto.UserItemInputDto;

public interface ReturnItemUseCase {
    RentalCardOutputDto returnItem(UserItemInputDto returnDto) throws Exception;
}
