package com.example.scheduler.service;

import com.example.scheduler.dto.SchedulerResponseDto;
import com.example.scheduler.entity.Member;
import com.example.scheduler.entity.Scheduler;
import com.example.scheduler.repository.MemberRepository;
import com.example.scheduler.repository.SchedulerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final MemberRepository memberRepository;
    private final SchedulerRepository schedulerRepository;

    // 일정 생성
    public SchedulerResponseDto save(String title, String contents, String email) {
        Member findMember = memberRepository.findMemberByEmailOrElseThrow(email);

        Scheduler scheduler = new Scheduler(title, contents);
        scheduler.setMember(findMember);

        schedulerRepository.save(scheduler);

        return new SchedulerResponseDto(scheduler.getId(), scheduler.getTitle(), scheduler.getContents());
    }

    // 일정 전체 조회
    public List<SchedulerResponseDto> findAll(){
        return schedulerRepository.findAll().stream().map(SchedulerResponseDto :: toDto).toList();
    }

    // 특정 게시물 삭제
    public void delete (Long id) {
        Scheduler findScheduler = schedulerRepository.findByIdOrElseThorw(id);

        schedulerRepository.delete(findScheduler);
    }




}
