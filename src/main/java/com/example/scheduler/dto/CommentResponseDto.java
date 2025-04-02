package com.example.scheduler.dto;

import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final String content;


    public CommentResponseDto(String content) {
        this.content = content;
    }
}
