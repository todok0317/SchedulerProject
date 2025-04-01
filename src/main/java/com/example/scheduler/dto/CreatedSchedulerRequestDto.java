package com.example.scheduler.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;


@Getter
public class CreatedSchedulerRequestDto {

    @NotBlank(message = "제목은 필수 입력값입니다.")
    private final String title;

    @NotBlank(message = "내용은 필수 입력값입니다.")
    private final String contents;

    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private final String email;

    public CreatedSchedulerRequestDto(String title, String contents, String email) {
        this.title = title;
        this.contents = contents;
        this.email = email;
    }


}
