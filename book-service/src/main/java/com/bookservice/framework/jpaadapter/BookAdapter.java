package com.bookservice.framework.jpaadapter;

import com.bookservice.application.exception.BaseException;
import com.bookservice.application.outputport.BookOutputPort;
import com.bookservice.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookAdapter implements BookOutputPort {

    private final BookRepository bookRepository;

    @Override
    public Book loadBook(Long bookNumber) {
        return bookRepository.findById(bookNumber)
                .orElseThrow(() -> new BaseException(String.format("Book Not Found by id : %d",bookNumber), HttpStatus.NOT_FOUND.value()));
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookNumber) {
        Book book = bookRepository.findById(bookNumber)
                .orElseThrow(() -> new BaseException(String.format("Book Not Found by id : %d",bookNumber), HttpStatus.NOT_FOUND.value()));
        bookRepository.delete(book);
    }
}
