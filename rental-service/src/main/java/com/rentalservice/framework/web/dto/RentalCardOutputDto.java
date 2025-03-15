package com.rentalservice.framework.web.dto;

import com.rentalservice.domain.model.RentalCard;
import com.rentalservice.domain.model.RentalItem;
import com.rentalservice.domain.model.vo.ReturnItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RentalCardOutputDto {
    private String rentalCardId;
    private String memberId;
    private String memberName;
    private String rentalStatus;
    private Long totalLateFee;
    //전체대여도서 리스트
    private List<RentalItem> rentalItemList;
    //전체반납도서 리스트
    private List<ReturnItem> returnItemList;
    //연체중인도서건수
    private Long totalOverDuedCnt;

    public static RentalCardOutputDto mapToDTO(RentalCard rental){
        RentalCardOutputDto rentDTO = new RentalCardOutputDto();
        rentDTO.setRentalCardId(rental.getRentalCardNo().getNo().toString());
        rentDTO.setMemberId(rental.getMember().getId().toString());
        rentDTO.setMemberName(rental.getMember().getName());
        rentDTO.setRentalStatus(rental.getRentalStatus().toString());

        rentDTO.setRentalItemList(rental.getRentalItemList());

        rentDTO.setReturnItemList(rental.getReturnItemList());

        rentDTO.setTotalOverDuedCnt(rental.getRentalItemList().stream()
                .filter(RentalItem::isOverDued).count());

        return rentDTO; }
}
