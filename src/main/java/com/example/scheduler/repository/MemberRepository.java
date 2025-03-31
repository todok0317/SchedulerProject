package com.example.scheduler.repository;

import com.example.scheduler.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    default Member findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "맞는 아이디가 없습니다."));
    }

    // 일정 생성
    Optional<Member> findMemberByUsername(String username);

    default Member findMemberByUsernameOrElseThrow(String username){
        return findMemberByUsername(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "작성자를 찾지 못했습니다"));
    }

}
