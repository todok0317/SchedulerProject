package com.example.scheduler.dto;

import com.example.scheduler.entity.Comment;
import com.example.scheduler.entity.Member;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    // 로그인 성립 POST /schedulers/{schedulerId}특정 일정조회/comments댓글달기 ->content

    private final String content;

    public CommentRequestDto(String content) {
        this.content = content;
    }
}


