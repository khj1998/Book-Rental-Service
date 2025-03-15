package com.rentalservice.domain.model;

import com.rentalservice.domain.model.event.ItemOverdueClearedEvent;
import com.rentalservice.domain.model.event.ItemRentedEvent;
import com.rentalservice.domain.model.event.ItemReturnedEvent;
import com.rentalservice.domain.model.vo.*;
import com.rentalservice.framework.web.exception.BasicException;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RentalCard {
    @EmbeddedId
    private RentalCardNo rentalCardNo;
    @Embedded
    private IdName member; // 카드 주인
    @Embedded
    private LateFee lateFee;

    private RentalStatus rentalStatus;

    // 빌린 아이템
    @Builder.Default
    @ElementCollection
    private List<RentalItem> rentalItemList = new ArrayList<>();
    // 반납한 아이템
    @Builder.Default
    @ElementCollection
    private List<ReturnItem> returnItemList = new ArrayList<>();

    public static RentalCard createRentalCard(IdName creator) {
        return RentalCard.builder()
                .rentalCardNo(RentalCardNo.createRentalCardNo())
                .member(creator)
                .rentalStatus(RentalStatus.RENTAL_AVAILABLE)
                .lateFee(LateFee.createLateFee())
                .build();
    }

    public static ItemRentedEvent createItemRentedEvent(IdName idName, Item item, long point) {
        return new ItemRentedEvent(idName,item,point);
    }

    public static ItemReturnedEvent createItemReturnedEvent(IdName idName, Item item, long point) {
        return new ItemReturnedEvent(idName,item,point);
    }

    public static ItemOverdueClearedEvent createOverdueClearedEvent(IdName idName, long point) {
        return new ItemOverdueClearedEvent(idName,point);
    }

    //대여처리
    public RentalCard rentItem(Item item) {
        checkRentalAvailable();
        this.addRentalItem(RentalItem.createRentalItem(item));
        return this;
    }

    //반납처리
    public RentalCard returnItem(Item item, LocalDate returnDate) {
        RentalItem rentalItem = this.rentalItemList.stream()
                .filter(i -> i.getItem().equals(item)).findFirst().get();
        calculateLateFee(rentalItem,returnDate);

        this.addReturnItem(ReturnItem.createRentalItem(rentalItem));
        this.removeRentalItem(rentalItem);
        return this;
    }

    //연체처리

    /**
     * 실제로는 스케줄러,배치를 통해 연체된 항목들을 체크하게 된다.
     * 논리적인 연체 처리 상태 업데이트가 잘 되는지 확인하기 위한 코드.
     */
    public RentalCard overdueItem(Item item) {
        RentalItem rentalItem = this.rentalItemList.stream().filter(i -> i.getItem().equals(item))
                .findFirst().get();
        rentalItem.setOverDued(true);

        this.rentalStatus = RentalStatus.RENTAL_UNAVAILABLE;
        rentalItem.setOverDueDate(LocalDate.now().minusDays(1));

        return this;
    }

    //연체 해제 처리
    public long makeAvailableRentalByPoint(long point) throws Exception {
        if (!this.rentalItemList.isEmpty()) {
            throw new BasicException("모든 도서가 반납되어야 정지를 해제할 수 있습니다.");
        }
        if (this.getLateFee().getPoint() != point) {
            throw new BasicException("해당 포인트로 연체를 해제할 수 없습니다.");
        }

        this.lateFee = lateFee.removePoint(point);

        if (this.getLateFee().getPoint() == 0) {
            this.rentalStatus = RentalStatus.RENTAL_AVAILABLE;
        }

        return this.getLateFee().getPoint();
    }

    //대여취소 보상 트랜젝션 로직
    public RentalCard cancelRentItem(Item item) throws Exception {
        RentalItem rentedItem = this.rentalItemList.stream().filter(i ->
                i.getItem().equals(item))
                .findFirst()
                .get();
        this.removeRentalItem(rentedItem);

        return this;
    }

    //반납취소 보상 트랜젝션 로직
    public RentalCard cancelReturnItem(Item item) throws Exception {
        ReturnItem returnItem = this.returnItemList.stream().filter(i ->
                i.getRentalItem().equals(item))
                .findFirst()
                .get();
        this.addRentalItem(returnItem.getRentalItem());
        this.removeReturnItem(returnItem);

        return this;
    }

    //대여정지해제 보상 트랜젝션 로직
    public long cancelMakeAvailableRental(long point) throws Exception {
        this.updateLateFee(point);
        this.rentalStatus = RentalStatus.RENTAL_UNAVAILABLE;
        return this.getLateFee().getPoint();
    }

    private void updateLateFee(long point) {
        this.lateFee = this.lateFee.addPoint(point);
    }

    private void removeReturnItem(ReturnItem returnItem) {
        this.getReturnItemList().remove(returnItem);
    }

    private void checkRentalAvailable() {
        if (this.rentalStatus == RentalStatus.RENTAL_UNAVAILABLE) {
            throw new BasicException("대여 불가 상태입니다.");
        }
        if (this.rentalItemList.size() >= 5) {
            throw new BasicException("이미 5권을 대여하였습니다.");
        }
    }

    private void calculateLateFee(RentalItem rentalItem,LocalDate returnDate) {
        if (returnDate.isAfter(rentalItem.getOverDueDate())) {
            int point = Period.between(rentalItem.getOverDueDate(),returnDate).getDays()*10;
            this.lateFee = this.lateFee.addPoint(point);
        }
    }

    private void addRentalItem(RentalItem rentalItem) {
        this.rentalItemList.add(rentalItem);
    }

    private void removeRentalItem(RentalItem rentalItem) {
        this.rentalItemList.remove(rentalItem);
    }

    private void addReturnItem(ReturnItem returnItem) {
        this.returnItemList.add(returnItem);
    }
}
