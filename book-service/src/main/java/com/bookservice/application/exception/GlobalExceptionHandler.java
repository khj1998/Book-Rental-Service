package com.bookservice.application.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseException> handleException(BaseException ex) {
        log.info("Exception has been occurred : {}",ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(ex);
    }
}
