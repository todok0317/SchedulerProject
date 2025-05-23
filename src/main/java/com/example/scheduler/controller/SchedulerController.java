package com.example.scheduler.controller;

import com.example.scheduler.dto.CreatedSchedulerRequestDto;
import com.example.scheduler.dto.SchedulerResponseDto;
import com.example.scheduler.dto.UpdateSchedulerRequestDto;
import com.example.scheduler.service.SchedulerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedulers")
@RequiredArgsConstructor
public class SchedulerController {
    private final SchedulerService schedulerService;

    // 일정 생성
    @PostMapping
    public ResponseEntity<SchedulerResponseDto> save(@Valid @RequestBody CreatedSchedulerRequestDto requestDto) {
        SchedulerResponseDto schedulerResponseDto = schedulerService.save(requestDto.getTitle(), requestDto.getContents(), requestDto.getEmail());

        return new ResponseEntity<>(schedulerResponseDto, HttpStatus.CREATED);
    }

    // 일정 전체 조회
    @GetMapping
    public ResponseEntity<List<SchedulerResponseDto>> findAll() {
        List<SchedulerResponseDto> schedulerResponseDtos = schedulerService.findAll();

        return new ResponseEntity<>(schedulerResponseDtos, HttpStatus.OK);
    }

    // 특정 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<SchedulerResponseDto> findById(@PathVariable Long id) {
        SchedulerResponseDto responseDto = schedulerService.findById(id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 특정 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<SchedulerResponseDto> delete (@PathVariable Long id) {
        schedulerService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 특정 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<SchedulerResponseDto> update(@PathVariable Long id, @Valid @RequestBody UpdateSchedulerRequestDto requestDto){
        SchedulerResponseDto schedulerResponseDto = schedulerService.update(id, requestDto.getTitle(), requestDto.getContents());

        return new ResponseEntity<>(schedulerResponseDto, HttpStatus.OK);
    }

}
