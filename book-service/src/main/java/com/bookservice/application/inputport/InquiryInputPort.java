package com.bookservice.application.inputport;

import com.bookservice.application.outputport.BookOutputPort;
import com.bookservice.application.usecase.InquiryUseCase;
import com.bookservice.domain.model.Book;
import com.bookservice.framework.web.dto.BookOutputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class InquiryInputPort implements InquiryUseCase {

    private final BookOutputPort bookOutputPort;

    @Override
    public BookOutputDto getBookInfo(Long bookNumber) {
        log.info("{} id 값으로 책 정보 조회 수행",bookNumber);
        Book loadedBook = bookOutputPort.loadBook(bookNumber);
        return BookOutputDto.mapToDTO(loadedBook);
    }
}
