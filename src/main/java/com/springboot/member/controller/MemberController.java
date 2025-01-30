package com.springboot.member.controller;

import com.springboot.member.dto.MemberPatchDto;
import com.springboot.member.dto.MemberPostDto;
import com.springboot.member.dto.MemberResponseDto;
import com.springboot.member.entity.Member;
import com.springboot.member.mapper.MemberMapper;
import com.springboot.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v11/members")
@Validated
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberPostDto) {
       Member member = memberService.createdMember(memberMapper.memberPostDtoToMember(memberPostDto));
       MemberResponseDto response = memberMapper.memberToMemberResponseDto(member);
       return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId,
                                      @RequestBody @Valid MemberPatchDto memberPatchDto) {
//        데이터 동기화.
        memberPatchDto.setMemberId(memberId);
        Member member = memberService.updatedMember(memberMapper.memberPatchDtoToMember(memberPatchDto));
        MemberResponseDto response = memberMapper.memberToMemberResponseDto(member);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId) {
        MemberResponseDto reesponse = memberMapper.memberToMemberResponseDto(memberService.findMember(memberId));
        return new ResponseEntity(reesponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() {
       List<MemberResponseDto> response = memberMapper.memberToMemberResponseDtos(memberService.findMembers());
       return new ResponseEntity(response,HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
