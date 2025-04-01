package com.example.scheduler.handler;

import com.example.scheduler.exception.EmailNotFoundException;
import com.example.scheduler.exception.InvalidPasswordException;
import com.example.scheduler.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {

    // 아이디를 찾을 수 없는 예외 처리
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getCode(),
                e.getMessage(),
                e.getStatus(),
                List.of(new FieldErrorResponse("username", e.getMessage()))
        );

        return new ResponseEntity<>(errorResponse, e.getStatus());
    }

    // 비밀번호가 틀린 예외 처리
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPasswordException(InvalidPasswordException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getCode(),
                e.getMessage(),
                e.getStatus(),
                List.of(new FieldErrorResponse("password", e.getMessage()))
        );
        return new ResponseEntity<>(errorResponse, e.getStatus());
    }

    // 이메일이 틀린 예외 처리
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmailNotFoundException(EmailNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getCode(),
                e.getMessage(),
                e.getStatus(),
                List.of(new FieldErrorResponse("password", e.getMessage()))
        );
        return new ResponseEntity<>(errorResponse, e.getStatus());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException (MethodArgumentNotValidException e){

        List<FieldErrorResponse> fieldErrors = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new FieldErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(new ErrorResponse("E001", "잘못된 입력값입니다.",HttpStatus.BAD_REQUEST, fieldErrors));
    }

    @Getter
    @AllArgsConstructor
    public static class ErrorResponse {
        private String code;
        private String message;
        private HttpStatus status;
        private List<FieldErrorResponse> errors;
    }
    @Getter
    @AllArgsConstructor
    public static class FieldErrorResponse{
        private String field;
        private String message;
    }






}
