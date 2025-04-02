package com.example.scheduler.service;

import com.example.scheduler.dto.CommentResponseDto;
import com.example.scheduler.entity.Comment;
import com.example.scheduler.entity.Member;
import com.example.scheduler.entity.Scheduler;
import com.example.scheduler.repository.CommentRepository;
import com.example.scheduler.repository.MemberRepository;
import com.example.scheduler.repository.SchedulerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final SchedulerRepository schedulerRepository;
    private final MemberRepository memberRepository;

    // 로직 방향: 유저 생성 -> 로그인 (멤버id 보유) -> 일정 생성 (스케쥴 id 보유) -> 일정 조회 (스케줄 id 받음) -> 댓글달기(멤버 id, 스케줄 id 확인)

    // 댓글 저장 기능
    public CommentResponseDto save(Long schedulerId, Long memberId, String content) {
        // 스케줄 멤버 id 조회
        Scheduler scheduler = schedulerRepository.findByIdOrElseThorw(schedulerId);
        Member member = memberRepository.findByIdOrElseThrow(memberId);

        if (!scheduler.getMember().getId().equals(memberId)) {
            throw new IllegalArgumentException("일정 작성자만 댓글을 달 수 있습니다.");
        }

        // 댓글 생성
        Comment comment = new Comment(content, member, scheduler);

        commentRepository.save(comment);
        return new CommentResponseDto(comment.getId(),comment.getContent());
    }
}
