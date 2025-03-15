package com.bookservice.application.inputport;
import com.bookservice.application.outputport.BookOutputPort;
import com.bookservice.application.usecase.MakeUnavailableUseCase;
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
public class MakeUnAvailableInputPort implements MakeUnavailableUseCase {

    private final BookOutputPort bookOutputPort;

    @Override
    public BookOutputDto makeUnavailable(Long bookNumber) {
        Book loadedBook = bookOutputPort.loadBook(bookNumber);

        log.info("{} 책 엔티티 대여 불가능한 상태로 수정",loadedBook.toString());
        loadedBook.makeUnavailable();
        return BookOutputDto.mapToDTO(loadedBook);
    }
}
