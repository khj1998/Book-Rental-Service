package com.userservice.framework.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{
    private final int statusCode;
    private final String message;

    public BaseException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }
}
