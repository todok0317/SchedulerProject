package com.example.scheduler.dto;

import com.example.scheduler.entity.Scheduler;
import lombok.Getter;

@Getter
public class SchedulerResponseDto {

    private final Long id;

    private final String title;

    private final String contents;

    public SchedulerResponseDto(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public static SchedulerResponseDto toDto (Scheduler scheduler){
        return new SchedulerResponseDto(scheduler.getId(), scheduler.getTitle(), scheduler.getContents());
    }


}
