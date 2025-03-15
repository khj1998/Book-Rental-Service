package com.rentalservice.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 연체료 VO
 * 연체 발생시 포인트를 부과 & 포인트를 제어하는 로직
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class LateFee {
    private long point;

    public static LateFee createLateFee() {
        return new LateFee(0);
    }

    public LateFee addPoint(long point) {
        return new LateFee(this.point + point);
    }

    public LateFee removePoint(long point) throws Exception {
        if (point > this.point) {
            throw new Exception("보유한 포인트보다 커서 삭제할 수 없습니다.");
        }

        return new LateFee(this.point - point);
    }
}
