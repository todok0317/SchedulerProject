package com.example.scheduler.repository;

import com.example.scheduler.entity.Member;
import com.example.scheduler.exception.CustomException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    default Member findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND,"M001", "맞는 아이디가 없습니다."));
    }

    // 일정 생성
    Optional<Member> findMemberByUsername(String username);

    default Member findMemberByUsernameOrElseThrow(String username){
        return findMemberByUsername(username).orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND,"M002", "작성자를 찾지 못했습니다"));
    }

    // 로그인 기능
    Optional<Member> findMemberByEmail(String email);

    default Member findMemberByEmailOrElseThrow(String email){
        return findMemberByEmail(email).orElseThrow(()-> new CustomException(HttpStatus.UNAUTHORIZED,"M003","이메일 또는 비밀번호가 틀렸습니다."));
    }



}
