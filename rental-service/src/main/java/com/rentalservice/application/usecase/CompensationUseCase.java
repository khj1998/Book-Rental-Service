package com.rentalservice.application.usecase;

import com.rentalservice.domain.model.RentalCard;
import com.rentalservice.domain.model.vo.IdName;
import com.rentalservice.domain.model.vo.Item;

public interface CompensationUseCase {
    RentalCard cancelRentItem(IdName idName, Item item);
    RentalCard cancelReturnItem(IdName idName,Item item,long point) throws Exception;
    long cancelMakeAvailableRental(IdName idName,long point);
}
