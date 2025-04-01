package com.example.scheduler.exception;

import org.springframework.http.HttpStatus;

public class EmailNotFoundException extends CustomException {
    public EmailNotFoundException(String message) {
        super(HttpStatus.UNAUTHORIZED, "M003", message);
    }
}
