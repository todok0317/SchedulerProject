package com.example.scheduler.dto;

import lombok.Getter;

@Getter
public class UpdateEmailRequestDto {

    private final String oldEmail;

    private final String newEmail;

    public UpdateEmailRequestDto(String oldEmail, String newEmail) {
        this.oldEmail = oldEmail;
        this.newEmail = newEmail;
    }
}
