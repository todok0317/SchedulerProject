package com.example.scheduler.exception;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends CustomException{
    public InvalidPasswordException(String message) {
        super(HttpStatus.UNAUTHORIZED, "M002", message);
    }
}
