package com.sarangBang.sarangBang.member.controller;

import com.sarangBang.sarangBang.member.domain.MbtiCode;
import com.sarangBang.sarangBang.member.domain.Member;
import com.sarangBang.sarangBang.member.domain.Sex;
import com.sarangBang.sarangBang.member.dto.MemberJoinFormDto;
import com.sarangBang.sarangBang.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @ModelAttribute("mbtiCodes")
    public List<MbtiCode> mbtiCodes() {
        List<MbtiCode> mbtiCodes = new ArrayList<>();
        setMbticodes(mbtiCodes);
        return mbtiCodes;
    }

    @ModelAttribute("sexes")
    public Sex[] sexes() {
        return Sex.values();
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

    private void setMbticodes(List<MbtiCode> mbtiCodes) {
        mbtiCodes.add(new MbtiCode("ISTJ", "istj"));
        mbtiCodes.add(new MbtiCode("ISFJ", "isfj"));
        mbtiCodes.add(new MbtiCode("INFJ", "infj"));
        mbtiCodes.add(new MbtiCode("INTJ", "intj"));
        mbtiCodes.add(new MbtiCode("ISTP", "istp"));
        mbtiCodes.add(new MbtiCode("ISFP", "isfp"));
        mbtiCodes.add(new MbtiCode("INFP", "infp"));
        mbtiCodes.add(new MbtiCode("INTP", "intp"));
        mbtiCodes.add(new MbtiCode("ESTP", "estp"));
        mbtiCodes.add(new MbtiCode("ESFP", "esfp"));
        mbtiCodes.add(new MbtiCode("ENFP", "enfp"));
        mbtiCodes.add(new MbtiCode("ENTP", "entp"));
        mbtiCodes.add(new MbtiCode("ESTJ", "estj"));
        mbtiCodes.add(new MbtiCode("ESFJ", "esfj"));
        mbtiCodes.add(new MbtiCode("ENFJ", "enfj"));
        mbtiCodes.add(new MbtiCode("ENTJ", "entj"));
    }

}
