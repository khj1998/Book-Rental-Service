package com.rentalservice.domain.model.event;

import com.rentalservice.domain.model.vo.IdName;
import com.rentalservice.domain.model.vo.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ItemRentedEvent implements Serializable {
    private IdName idName;
    private Item item;
    private long point;
}
