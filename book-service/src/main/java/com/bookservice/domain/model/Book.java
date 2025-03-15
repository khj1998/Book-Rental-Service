package com.bookservice.domain.model;

import com.bookservice.domain.model.vo.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookNumber;
    @Embedded
    private BookDescription description;

    private String title;
    private Classification classification;
    private BookStatus bookStatus;
    private Location location;

    public static Book enterBook(String title,
                                 String author,
                                 String isbn,
                                 String description,
                                 LocalDate publicationDate,
                                 Source source,
                                 Classification classfication,
                                 Location location) {
        BookDescription bookDesc = BookDescription.builder()
                .description(description)
                .author(author)
                .isbn(isbn)
                .publishedAt(publicationDate)
                .source(source)
                .build();

        return Book.builder()
                .title(title)
                .description(bookDesc)
                .classification(classfication)
                .location(location)
                .bookStatus(BookStatus.ENTERED)
                .build();
    }

    public Book makeAvailable() {
        this.bookStatus = BookStatus.AVAILABLE;
        return this;
    }

    public Book makeUnavailable() {
        this.bookStatus = BookStatus.UNAVAILABLE;
        return this;
    }
}
