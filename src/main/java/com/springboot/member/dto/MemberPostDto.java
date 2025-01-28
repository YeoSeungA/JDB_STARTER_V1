package com.springboot.member.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class MemberPostDto {
    @NotBlank(message = "이름은 공백이 아니어야 합니다.")
    private String name;

    @NotBlank
    @Email
    private String email;

    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$")
    private String phone;
}
