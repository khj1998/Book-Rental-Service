package com.bookservice.application.usecase;

import com.bookservice.framework.web.dto.BookOutputDto;

public interface MakeUnavailableUseCase {
    BookOutputDto makeUnavailable(Long bookNumber);
}
