package com.example.scheduler.service;

import com.example.scheduler.config.PasswordEncoder;
import com.example.scheduler.dto.LoginRequestDto;
import com.example.scheduler.dto.LoginResponseDto;
import com.example.scheduler.dto.MemberResponseDto;
import com.example.scheduler.dto.SignUpResponseDto;
import com.example.scheduler.entity.Member;
import com.example.scheduler.exception.InvalidPasswordException;
import com.example.scheduler.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 유저 생성 회원가입
    public SignUpResponseDto signUp(String username, String email, String password) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        Member member = new Member(username, email, encodedPassword);
        Member savedMember = memberRepository.save(member);

        return new SignUpResponseDto(savedMember.getId(), savedMember.getUsername(), savedMember.getEmail());
    }

    // 특정 유저 조회
    public MemberResponseDto findById(Long id) {
        Member findMember = memberRepository.findByIdOrElseThrow(id);

        return new MemberResponseDto(findMember.getUsername(), findMember.getEmail());
    }


    // 이메일 수정
    @Transactional
    public void updateEmail(Long id, String oldEmail, String newEmail) {
        Member findMember = memberRepository.findByIdOrElseThrow(id);

        if(!findMember.getEmail().equals(oldEmail)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일이 일치하지 않습니다.");
        }

        findMember.updateEmail(newEmail);
    }

    // 특정 유저 삭제
    public void delete (Long id) {
        Member findMember = memberRepository.findByIdOrElseThrow(id);

        memberRepository.delete(findMember);
    }

    // 로그인
    public LoginResponseDto login (LoginRequestDto requestDto, HttpServletRequest request) {
        Member member = memberRepository.findMemberByEmailOrElseThrow(requestDto.getEmail());

        if(!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new InvalidPasswordException("비밀번호가 올바르지 않습니다.");
        }

        HttpSession session = request.getSession();
        session.setAttribute("memberId", member.getId());

        return new LoginResponseDto(member.getUsername(), "로그인 성공");
    }

    // 로그아웃
    public void logout (HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
    }


}
