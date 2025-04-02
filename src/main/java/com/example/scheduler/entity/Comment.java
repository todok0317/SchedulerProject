package com.example.scheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "schedulr_id", nullable = false)
    private Scheduler scheduler;


    public Comment(String content, Member member, Scheduler scheduler) {
        this.content = content;
        this.member = member;
        this.scheduler = scheduler;
    }

    public Comment() {

    }

    public void update(String content){
        this.content = content;
    }

}
