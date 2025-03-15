package com.rentalservice.domain.model;

import com.rentalservice.domain.model.vo.Item;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class RentalItem {
    @Embedded
    private Item item;
    private LocalDate rentDate;

    // 연체가 되었는지 확인
    private boolean isOverDued;
    private LocalDate overDueDate;

    public static RentalItem createRentalItem(Item item) {
        return RentalItem.builder()
                .item(item)
                .rentDate(LocalDate.now())
                .overDueDate(LocalDate.now().plusDays(14))
                .isOverDued(false)
                .build();
    }
}
