package com.rentalservice.domain.model.event;

import com.rentalservice.domain.model.vo.IdName;
import com.rentalservice.domain.model.vo.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemReturnedEvent extends ItemRentedEvent {
    public ItemReturnedEvent(IdName idName, Item item, long point) {
        super(idName, item, point);
    }
}
