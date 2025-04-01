package com.example.scheduler.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(String message) {
        super(HttpStatus.UNAUTHORIZED, "M001" ,message);
    }
}
