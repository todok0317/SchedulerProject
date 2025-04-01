package com.example.scheduler.controller;

import com.example.scheduler.dto.*;
import com.example.scheduler.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 유저 생성 회원가입
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@Valid @RequestBody SignUpRequestDto requestDto) {
        SignUpResponseDto signUpResponseDto = memberService.signUp(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    // 유저 특정 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id){
        MemberResponseDto memberResponseDto = memberService.findById(id);

        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    // 이메일 수정
    @PatchMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateEmail(@PathVariable Long id, @RequestBody UpdateEmailRequestDto requestDto) {
        memberService.updateEmail(id, requestDto.getOldEmail(), requestDto.getNewEmail());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 특정 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<MemberResponseDto> delete(@PathVariable Long id) {
        memberService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 로그인 기능
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto, HttpServletRequest request) {
        LoginResponseDto responseDto = memberService.login(requestDto, request);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 로그아웃 기능
    @PostMapping("/logout")
    public ResponseEntity<Void> logout (HttpServletRequest request) {
        memberService.logout(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
