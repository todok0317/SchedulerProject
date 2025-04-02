package com.example.scheduler.repository;

import com.example.scheduler.entity.Member;
import com.example.scheduler.exception.EmailNotFoundException;
import com.example.scheduler.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    default Member findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new UserNotFoundException("맞는 아이디가 없습니다."));
    }

    // 로그인 기능
    Optional<Member> findMemberByEmail(String email);

    default Member findMemberByEmailOrElseThrow(String email){
        return findMemberByEmail(email).orElseThrow(()-> new EmailNotFoundException("이메일 또는 비밀번호가 틀렸습니다."));
    }



}
