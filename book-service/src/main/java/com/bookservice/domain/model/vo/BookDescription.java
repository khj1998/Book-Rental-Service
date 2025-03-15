package com.bookservice.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class BookDescription {
    private String description;
    private String author;
    private String isbn;
    private LocalDate publishedAt;
    private Source source;
}
