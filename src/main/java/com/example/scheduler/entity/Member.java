package com.example.scheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Table(name = "member")
public class Member extends BaseEntity {

    // CascadeType.ALL을 사용하면 연관된 일정도 함께 삭제됨
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Scheduler> schedulers;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    public Member() {

    }

    public void updateEmail (String email) {
        this.email = email;
    }

    public Member(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
