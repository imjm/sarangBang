package com.sarangBang.sarangBang.member.domain;

import com.sarangBang.sarangBang.member.dto.MemberJoinFormDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Member {
    
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String college;

    private String mbtiCode;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    private Role role;

    private int count;

    private boolean isMatched;

    public static Member createMember(MemberJoinFormDto form, PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .username(form.getUsername())
                .college(form.getCollege())
                .sex(form.getSex())
                .mbtiCode(form.getMbtiCode())
                .role(Role.USER)
                .build();
        return member;
    }
}
