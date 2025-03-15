package com.bestbookservice.domain.model;

import com.bestbookservice.domain.vo.Item;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BestBook {
    @Id
    private String id;
    private Item item;
    private long rentCount;

    public void updateItem(Item item) {
        this.item = item;
    }

    public void updateRentCount(long rentCount) {
        this.rentCount = rentCount;
    }

    public static BestBook registerBestBook(Item item) {
        return BestBook.builder()
                .id(UUID.randomUUID().toString())
                .item(item)
                .rentCount(1L)
                .build();
    }

    public Long increaseBestBookCount() {
        this.rentCount = this.getRentCount() + 1;
        return this.rentCount;
    }
}
