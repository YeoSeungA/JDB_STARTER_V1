package com.springboot.member.mapper;

import com.springboot.member.dto.MemberPatchDto;
import com.springboot.member.dto.MemberPostDto;
import com.springboot.member.dto.MemberResponseDto;
import com.springboot.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring"/*, unmappedTargetPolicy = ReportingPolicy.IGNORE*/)
public interface MemberMapper {
//    PostDto를 Member Entity로
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
//    Patch를 Member Entity로
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
//    Member Entity를 MemberRespons Dto로
    MemberResponseDto memberToMemberResponseDto(Member member);
//    List<Member> Entity를 List<MemberResponseDto>로
    List<MemberResponseDto> memberToMemberResponseDtos(List<Member>members);
}
