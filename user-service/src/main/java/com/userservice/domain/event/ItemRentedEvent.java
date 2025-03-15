package com.userservice.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.userservice.domain.vo.IdName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ItemRentedEvent implements Serializable {
    @JsonProperty("idName")
    private IdName idName;
    @JsonProperty("item")
    private Item item;
    private long point;
}
