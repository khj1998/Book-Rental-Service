package com.bookservice.domain.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemReturnedEvent extends ItemRentedEvent {
    public ItemReturnedEvent(IdName idName, Item item, long point) {
        super(idName, item, point);
    }
}
