package com.example.scheduler.controller;

import com.example.scheduler.dto.*;
import com.example.scheduler.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedulers/{schedulerId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping
    public ResponseEntity<CommentResponseDto> saveComment (@PathVariable Long schedulerId, HttpServletRequest request, @Valid @RequestBody CommentRequestDto requestDto){
        // 로그인 id 받아오기
        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        // 로그인 확인 여부
        if(memberId == null ) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        CommentResponseDto commentResponseDto = commentService.save(schedulerId, memberId, requestDto.getContent());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    // 특정 일정의 댓글 전체 조회
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAll() {
        List<CommentResponseDto> commentResponseDtos = commentService.findAll();

        return new ResponseEntity<>(commentResponseDtos, HttpStatus.OK);
    }

    // 특정 일정의 특정 댓글 조회
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> findById(@PathVariable Long id) {
        CommentResponseDto responseDto = commentService.findById(id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 특정 일정 특정 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> update(@PathVariable Long id, @Valid @RequestBody UpdateCommentRequestDto requestDto){
        CommentResponseDto commentResponseDto = commentService.update(id, requestDto.getContent());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    // 특정 일정의 특정 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<CommentResponseDto> delete (@PathVariable Long id) {
        commentService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
