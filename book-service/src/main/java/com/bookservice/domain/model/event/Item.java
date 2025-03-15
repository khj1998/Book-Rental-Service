package com.bookservice.domain.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @JsonProperty("itemNo")
    private Long itemNo;
    @JsonProperty("title")
    private String title;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Item that = (Item) obj;
        return itemNo.equals(that.itemNo);
    }
}
