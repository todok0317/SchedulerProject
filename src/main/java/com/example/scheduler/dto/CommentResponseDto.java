package com.example.scheduler.dto;

import com.example.scheduler.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final Long id;
    private final String content;


    public CommentResponseDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public static CommentResponseDto toDto (Comment comment){
        return new CommentResponseDto(comment.getId(), comment.getContent());
    }


}
