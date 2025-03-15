package com.rentalservice.application.usecase;


import com.rentalservice.framework.web.dto.RentalCardOutputDto;
import com.rentalservice.framework.web.dto.UserInputDto;

public interface CreateRentalCardUseCase {
    RentalCardOutputDto createRentalCard(UserInputDto userInputDto);
}
