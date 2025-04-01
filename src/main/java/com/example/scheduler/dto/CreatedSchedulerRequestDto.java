package com.example.scheduler.dto;

import lombok.Getter;

@Getter
public class CreatedSchedulerRequestDto {


    private final String title;

    private final String contents;

    private final String email;

    public CreatedSchedulerRequestDto(String title, String contents, String email) {
        this.title = title;
        this.contents = contents;
        this.email = email;
    }


}
