package com.example.scheduler.controller;

import com.example.scheduler.dto.CommentRequestDto;
import com.example.scheduler.dto.CommentResponseDto;
import com.example.scheduler.service.CommentService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
