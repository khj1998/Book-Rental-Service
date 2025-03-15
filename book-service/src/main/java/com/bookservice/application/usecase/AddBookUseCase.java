package com.bookservice.application.usecase;

import com.bookservice.framework.web.dto.BookInfoDto;
import com.bookservice.framework.web.dto.BookOutputDto;

public interface AddBookUseCase {
    BookOutputDto addBook(BookInfoDto bookInfoDto);
}
