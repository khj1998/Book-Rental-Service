package com.bookservice.application.inputport;

import com.bookservice.application.outputport.BookOutputPort;
import com.bookservice.application.usecase.AddBookUseCase;
import com.bookservice.domain.model.Book;
import com.bookservice.domain.model.vo.Classification;
import com.bookservice.domain.model.vo.Location;
import com.bookservice.domain.model.vo.Source;
import com.bookservice.framework.web.dto.BookInfoDto;
import com.bookservice.framework.web.dto.BookOutputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AddBookIInputPort implements AddBookUseCase {

    private final BookOutputPort bookOutputPort;

    @Override
    public BookOutputDto addBook(BookInfoDto bookInfoDto) {
        log.info("Addming book : {}",bookInfoDto.toString());

        Book book = Book.enterBook(
                bookInfoDto.getTitle(),
                bookInfoDto.getAuthor(),
                bookInfoDto.getIsbn(),
                bookInfoDto.getDescription(),
                bookInfoDto.getPublicationDate(),                 
                Source.valueOf(bookInfoDto.getSource()),
                Classification.valueOf(bookInfoDto.getClassfication()),
                Location.valueOf(bookInfoDto.getLocation())
        );

        Book save = bookOutputPort.save(book);
        return BookOutputDto.mapToDTO(save);
    }
}
