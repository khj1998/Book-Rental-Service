package com.bestbookservice.domain.event;

import com.bestbookservice.domain.vo.IdName;
import com.bestbookservice.domain.vo.Item;
import com.fasterxml.jackson.annotation.JsonProperty;
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
