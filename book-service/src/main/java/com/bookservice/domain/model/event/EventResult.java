package com.bookservice.domain.model.event;

import com.bookservice.domain.model.vo.EventType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EventResult implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private EventType eventType;
    @JsonProperty("isSuccessed")
    private boolean isSuccessed;
    private IdName idName;
    private Item item;
    private long point;
}
