package com.springboot.member.dto;

import com.springboot.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberResponseDto {
    private long memberId;
    private String name;
    private String email;
    private String phone;
    private Member.MemberStatus memberStatus;
}
