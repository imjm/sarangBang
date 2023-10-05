package com.sarangBang.sarangBang.member.service;

import com.sarangBang.sarangBang.member.domain.Mbti;
import com.sarangBang.sarangBang.member.domain.Member;
import com.sarangBang.sarangBang.member.domain.Sex;
import com.sarangBang.sarangBang.member.dto.MemberJoinFormDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void 회원가입_테스트() {
        MemberJoinFormDto form = MemberJoinFormDto.builder()
                .email("test@naver.com")
                .password("1111")
                .username("test")
                .college("공대")
                .sex(Sex.MALE)
                .mbti(Mbti.ENFP)
                .build();
        Member member = Member.createMember(form, passwordEncoder);
        Member savedMember = memberService.saveMember(member);

        Assertions.assertEquals(member.getEmail(), savedMember.getEmail());
    }

}