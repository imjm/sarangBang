package com.sarangBang.sarangBang.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class User {
    
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String college;

    @Enumerated(EnumType.STRING)
    private Mbti mbti;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private int count;

    private boolean isMatched;
}
