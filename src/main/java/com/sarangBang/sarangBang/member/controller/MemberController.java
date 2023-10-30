package com.sarangBang.sarangBang.member.controller;

import com.sarangBang.sarangBang.member.domain.Member;
import com.sarangBang.sarangBang.member.dto.MemberJoinFormDto;
import com.sarangBang.sarangBang.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/join")
    public String memberJoinForm(Model model) {
        log.info("memberJoinForm");
        model.addAttribute("memberJoinFormDto", new MemberJoinFormDto());
        return "member/memberForm";
    }

    @PostMapping("/join")
    public String memberJoin(@Valid MemberJoinFormDto form, BindingResult bindingResult, Model model) {
        log.info("memberJoin");
        if (bindingResult.hasErrors()) {
            return "member/memberForm";
        }

        try {
            Member member = Member.createMember(form, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String memberLoginForm() {
        return "/member/memberLoginForm";
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인하세요");
        System.out.println("로그인 실패");
        return "redirect:/members/login";
    }


}
