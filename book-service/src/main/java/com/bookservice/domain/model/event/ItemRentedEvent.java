package com.bookservice.domain.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemRentedEvent implements Serializable {
    @JsonProperty("idName")
    private IdName idName;
    @JsonProperty("item")
    private Item item;
    @JsonProperty("point")
    private long point;
}
