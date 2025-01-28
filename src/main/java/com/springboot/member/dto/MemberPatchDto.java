package com.springboot.member.dto;

import com.springboot.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class MemberPatchDto {
//    Controller에서 필요
    @Setter
    private long memberId;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$")
    private String phone;

    private Member.MemberStatus memberStatus;
////    Controller 에서 Patch 요청을 받을시 데이터 동화를 위해 setMemberId 함수가 필요하다.
//    public void setMemberId (long memberId) {
//        this.memberId = memberId;
//    }
}
