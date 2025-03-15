package com.bookservice.framework.web.dto;

import com.bookservice.domain.model.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookOutputDto {
    private long bookNo;
    private String bookTitle;
    private String bookStatus;

    public static BookOutputDto mapToDTO(Book book)
    {
        BookOutputDto bookOutPutDTO = new BookOutputDto();
        bookOutPutDTO.setBookNo(book.getBookNumber());
        bookOutPutDTO.setBookTitle(book.getTitle());
        bookOutPutDTO.setBookStatus(book.getBookStatus().toString());
        return bookOutPutDTO;
    }
}
