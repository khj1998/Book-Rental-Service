package com.bookservice.framework.web;

import com.bookservice.application.usecase.AddBookUseCase;
import com.bookservice.application.usecase.DeleteBookUseCase;
import com.bookservice.application.usecase.InquiryUseCase;
import com.bookservice.framework.web.dto.BookInfoDto;
import com.bookservice.framework.web.dto.BookOutputDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
    private final AddBookUseCase addBookUseCase;
    private final DeleteBookUseCase deleteBookUseCase;
    private final InquiryUseCase inquiryUseCase;

    @PostMapping("/book")
    @Operation(summary = "새로운 책을 등록하는 api")
    public ResponseEntity<BookOutputDto> createBook(@RequestBody BookInfoDto bookInfoDto) {
        BookOutputDto BookOutputDto = addBookUseCase.addBook(bookInfoDto);
        return new ResponseEntity<>(BookOutputDto, HttpStatus.CREATED);
    }

    @GetMapping("/book/{no}")
    @Operation(summary = "특정 책 정보를 조회하는 api")
    public ResponseEntity<BookOutputDto> getBookInfo(@PathVariable("no") String no) {
        BookOutputDto bookInfo = inquiryUseCase.getBookInfo(Long.parseLong(no));
        return bookInfo != null
                ? new ResponseEntity<>(bookInfo, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/book/{no}")
    @Operation(summary = "특정 책을 삭제하는 api")
    public ResponseEntity<?> deleteBook(@PathVariable("no") String no) {
        deleteBookUseCase.deleteBookByBookNumber(Long.parseLong(no));
        return ResponseEntity.ok().build();
    }
}
