package com.bestbookservice.service;

import com.bestbookservice.domain.model.BestBook;
import com.bestbookservice.domain.vo.Item;
import com.bestbookservice.exception.BaseException;
import com.bestbookservice.repository.BestBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BestBookService {
    private final BestBookRepository bookRepository;

    public List<BestBook> getAllBooks() {
        log.info("모든 Best Book 조회");
        return bookRepository.findAll();
    }

    public BestBook getBookById(String id) {
        log.info("id {} 값으로 best book 조회",id);
        return bookRepository.findById(id)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND.value(),"book has not been found"));
    }

    public void dealBestBook(Item item){
        Optional<BestBook> bestBook = bookRepository.findBestBookByItem(item);

        if (bestBook.isPresent()){
            log.info("BestBook 카운트 증가, id : {}",bestBook.get().getId());
            bestBook.get().increaseBestBookCount();
        } else {
            log.info("BestBook 등록 수행");
            BestBook newBestBook =  BestBook.registerBestBook(item);
            saveBook(newBestBook);
        }
    }

    public BestBook updateBook(String id, BestBook book) {
        BestBook existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND.value(),"book has not been found"));
        existingBook.updateItem(book.getItem());
        existingBook.updateRentCount(book.getRentCount());

        return bookRepository.save(existingBook);
    }

    public boolean deleteBook(String id) {
        Optional<BestBook> bookOptional = bookRepository.findById(id);

        if (bookOptional.isPresent()) {
            bookRepository.delete(bookOptional.get()); return true;
        }
        return false;
    }

    public BestBook saveBook(BestBook book) {
        return bookRepository.save(book);
    }
}
