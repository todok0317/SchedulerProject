package com.example.scheduler.dto;

import lombok.Getter;

@Getter
public class SignUpRequestDto {

    private final String username;

    private final String email;

    public SignUpRequestDto(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
