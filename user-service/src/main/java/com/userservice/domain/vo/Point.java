package com.userservice.domain.vo;

import com.userservice.framework.exception.BaseException;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Point {
    private long pointValue ;

    public long addPoint(long point){
        this.setPointValue(this.pointValue + point);
        return this.pointValue;
    }

    public long removePoint(long point) {
        if(point > this.pointValue) {
            throw new BaseException(HttpStatus.BAD_REQUEST.value(), "기존 보유 Point보다 적어 삭제할 수 없습니다.");
        }
        this.setPointValue(this.pointValue - point);return this.pointValue;
    }

    public static Point createPoint() {
        return new Point(0L);
    }
}
