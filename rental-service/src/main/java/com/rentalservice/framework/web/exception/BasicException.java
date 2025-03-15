package com.rentalservice.framework.web.exception;

import lombok.Getter;

@Getter
public class BasicException extends RuntimeException {
    private String message;

    public BasicException(String message) {
        super(message);
        this.message = message;
    }
}
