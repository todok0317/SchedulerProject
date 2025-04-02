package com.example.scheduler.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotBlank(message = "필수 입력값입니다.")
    private final String content;

    public CommentRequestDto(String content) {
        this.content = content;
    }
}


