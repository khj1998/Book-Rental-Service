package com.bookservice.application.usecase;

import com.bookservice.framework.web.dto.BookOutputDto;

public interface MakeAvailableUseCase {
    BookOutputDto makeAvailable(Long bookNumber);
}
