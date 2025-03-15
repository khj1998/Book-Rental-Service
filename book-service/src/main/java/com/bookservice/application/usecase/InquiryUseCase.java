package com.bookservice.application.usecase;

import com.bookservice.framework.web.dto.BookOutputDto;

public interface InquiryUseCase {
    BookOutputDto getBookInfo(Long bookNumber);
}
