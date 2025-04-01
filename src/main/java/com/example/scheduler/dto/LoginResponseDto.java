package com.example.scheduler.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private final String username;
    private final String message;

    public LoginResponseDto(String username, String message) {
        this.username = username;
        this.message = message;
    }
}
