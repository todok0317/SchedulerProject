package com.example.scheduler.repository;

import com.example.scheduler.entity.Scheduler;
import com.example.scheduler.exception.CustomException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface SchedulerRepository extends JpaRepository<Scheduler, Long> {

    default Scheduler findByIdOrElseThorw(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND,"C002" ,"해당 아이디가 없습니다."));
    }

}
