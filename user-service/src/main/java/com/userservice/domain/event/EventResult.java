package com.userservice.domain.event;

import com.userservice.domain.vo.EventType;
import com.userservice.domain.vo.IdName;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EventResult {
    private EventType eventType;
    private boolean isSuccessed;
    private IdName idName;
    private Item item;
    private long point;
}
