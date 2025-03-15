package com.rentalservice.framework.web.dto;

import com.rentalservice.domain.model.RentalCard;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalResultOutputDto {
    public String userId;
    public String userName;
    public Integer rentedCount;
    public long totalLateFee;

    public static RentalResultOutputDto mapToDTO(RentalCard rental) {
        RentalResultOutputDto rentDTO = new RentalResultOutputDto();
        rentDTO.setUserId(rental.getMember().getId());
        rentDTO.setUserName(rental.getMember().getName());
        rentDTO.setRentedCount(rental.getRentalItemList().size());
        rentDTO.setTotalLateFee(rental.getLateFee().getPoint());

        return rentDTO;
    }
}
