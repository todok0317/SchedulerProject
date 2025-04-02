package com.example.scheduler.repository;

import com.example.scheduler.entity.Comment;
import com.example.scheduler.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    default Comment findByIdOrElseThorw(Long id) {
        return findById(id).orElseThrow(() -> new UserNotFoundException("해당 아이디가 없습니다."));
    }



}
