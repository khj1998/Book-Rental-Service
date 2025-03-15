package com.rentalservice.application.usecase;

import com.rentalservice.framework.web.dto.ClearOverDueInfoDto;
import com.rentalservice.framework.web.dto.RentalResultOutputDto;

/**
 * 연체 해제 처리 use case
 */
public interface ClearOverDueItemUseCase {
    RentalResultOutputDto clearOverDue(ClearOverDueInfoDto clearOverDueInfoDto) throws Exception;
}
