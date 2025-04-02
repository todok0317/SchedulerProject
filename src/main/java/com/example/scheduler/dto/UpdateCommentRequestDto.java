package com.example.scheduler.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateCommentRequestDto {

    @NotBlank(message = "필수 입력값입니다.")
    private String content;

}
