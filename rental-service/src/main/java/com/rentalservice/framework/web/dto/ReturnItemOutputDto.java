package com.rentalservice.framework.web.dto;

import com.rentalservice.domain.model.vo.ReturnItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReturnItemOutputDto {
    private Integer itemNo;
    private String itemTitle;
    private LocalDate returnDate;

    public static ReturnItemOutputDto mapToDTO(ReturnItem returnItem) {
        ReturnItemOutputDto rentItemOutputDTO = new ReturnItemOutputDto();
        rentItemOutputDTO.setItemNo(returnItem.getRentalItem().getItem().getItemNo());
        rentItemOutputDTO.setItemTitle(returnItem.getRentalItem().getItem().getTitle()); ;
        rentItemOutputDTO.setReturnDate(returnItem.getReturnDate());
        return rentItemOutputDTO;
    }
}
