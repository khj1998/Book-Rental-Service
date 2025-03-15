package com.rentalservice.domain.model.event;

import com.rentalservice.domain.model.vo.EventType;
import com.rentalservice.domain.model.vo.IdName;
import com.rentalservice.domain.model.vo.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventResult {
    private EventType eventType;
    private Boolean isSuccessed;
    private IdName idName;
    private Item item;
    private long point;
}
