package com.bookservice.application.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final int statusCode;
    private final String message;

    public BaseException(String message,int statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }
}
