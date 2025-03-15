package com.rentalservice.framework.web.dto;

import com.rentalservice.domain.model.RentalItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RentItemOutputDto {
    private Integer itemNo;
    private String itemTitle;
    private LocalDate rentDate;
    private boolean overDued;
    //반납 예정일
    private LocalDate overdueDate;

    public static RentItemOutputDto mapToDTO(RentalItem rentItem)
    {
        RentItemOutputDto rentItemOutputDTO = new RentItemOutputDto();
        rentItemOutputDTO.setItemNo(rentItem.getItem().getItemNo());
        rentItemOutputDTO.setItemTitle(rentItem.getItem().getTitle());
        rentItemOutputDTO.setRentDate(rentItem.getRentDate());
        rentItemOutputDTO.setOverDued(rentItem.isOverDued());
        rentItemOutputDTO.setOverdueDate(rentItem.getOverDueDate());
        return rentItemOutputDTO;
    }
}
