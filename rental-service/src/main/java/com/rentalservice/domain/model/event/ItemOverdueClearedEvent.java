package com.rentalservice.domain.model.event;

import com.rentalservice.domain.model.vo.IdName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ItemOverdueClearedEvent implements Serializable {
    private IdName idName;
    private long point;
}
