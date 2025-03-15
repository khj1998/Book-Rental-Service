package com.bookservice.application.outputport;

import com.bookservice.domain.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOutputPort {
    Book loadBook(Long bookNumber);
    Book save(Book book);
    void deleteBook(Long bookNumber);
}
