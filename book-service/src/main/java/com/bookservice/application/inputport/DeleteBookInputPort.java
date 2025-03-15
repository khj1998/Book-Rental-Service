package com.bookservice.application.inputport;

import com.bookservice.application.outputport.BookOutputPort;
import com.bookservice.application.usecase.DeleteBookUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DeleteBookInputPort implements DeleteBookUseCase {
    private final BookOutputPort bookOutputPort;

    @Override
    public void deleteBookByBookNumber(Long bookNumber) {
        log.info("{} id 값으로 Book 삭제 수행",bookNumber);
        bookOutputPort.deleteBook(bookNumber);
    }
}
