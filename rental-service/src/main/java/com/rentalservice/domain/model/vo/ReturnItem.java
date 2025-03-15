package com.rentalservice.domain.model.vo;

import com.rentalservice.domain.model.RentalItem;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * 반납한 아이템
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ReturnItem {
    @Embedded
    private RentalItem rentalItem;
    private LocalDate returnDate;

    public static ReturnItem createRentalItem(RentalItem rentalItem) {
        return new ReturnItem(rentalItem,LocalDate.now());
    }
}
