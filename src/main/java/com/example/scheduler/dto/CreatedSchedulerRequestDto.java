package com.example.scheduler.dto;

import lombok.Getter;

@Getter
public class CreatedSchedulerRequestDto {


    private final String title;

    private final String contents;

    private final String username;

    public CreatedSchedulerRequestDto(String title, String contents, String username) {
        this.title = title;
        this.contents = contents;
        this.username = username;
    }


}
