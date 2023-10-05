package com.sarangBang.sarangBang.member.dto;

import com.sarangBang.sarangBang.member.domain.Mbti;
import com.sarangBang.sarangBang.member.domain.Sex;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberJoinFormDto {

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String username;

    @NotBlank(message = "단과대는 필수 입력 값입니다.")
    private String college;

    private Mbti mbti;

    private Sex sex;
}
