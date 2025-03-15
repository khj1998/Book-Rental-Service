package com.rentalservice.framework.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BasicException.class)
    public ResponseEntity<BasicException> handleException(BasicException ex) {
        log.info("Exception has been occurred : {}",ex.getMessage());
        return ResponseEntity.ok(ex);
    }
}
