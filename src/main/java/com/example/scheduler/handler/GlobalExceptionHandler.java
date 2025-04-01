package com.example.scheduler.handler;

import com.example.scheduler.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public Map<String, Object> handleCustomException(CustomException ex, HttpServletRequest request) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", ex.getStatus().value(),
                "error", ex.getStatus().getReasonPhrase(),
                "code", ex.getCode(),
                "message", ex.getMessage(),
                "path", request.getRequestURI(),
                "fieldErrors", Collections.emptyList()
        );
    }

    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleException(Exception ex, HttpServletRequest request) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "code", "S001",
                "message", "서버 내부 오류가 발생했습니다.",
                "path", request.getRequestURI(),
                "fieldErrors", Collections.emptyList()
        );
    }
}
