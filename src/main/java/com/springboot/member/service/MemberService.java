package com.springboot.member.service;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExcepionCode;
import com.springboot.member.entity.Member;
import com.springboot.member.repository.MemberRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Service
public class MemberService {
//    Data Acceess 계층 주입 final을 사용해 초기화 후 변경할 수 없는 변수로 선언.
//    의존성 주입으로 사용하는 객체는 항상 final로 선언하는 것이 권장된다.
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createdMember(Member member) {
//        존재하는 회원인지 확인 없을때만 createdMember 실행
        verifiedMember(member.getEmail());
//        반환 값이 저장되 객체로, DB에서 ID 등이 업데이트 된 정보를 포함한다. 저장된 결과 반환
        return memberRepository.save(member);
//        return member;
    }

    public Member updatedMember(Member member) {
//        존재하는 회원인지 확인
        Member pathMember = existMember(member.getMemberId());
//  변경이 없는 값은 기존 값 유지, 변경이 있으면 변경값으로 update
        Optional.ofNullable(pathMember.getName())
                .ifPresent(name -> member.setName(name));
        Optional.ofNullable(pathMember.getPhone())
                .ifPresent(phone -> member.setPhone(phone));
        Optional.ofNullable(pathMember.getMemberStatus())
                .ifPresent(memberStatus -> member.setMemberStatus(memberStatus));

        return memberRepository.save(pathMember);
    }

    public Member findMember(long memberId) {
        return existMember(memberId);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public void deleteMember(long memberId) {
        Member member = existMember(memberId);
        memberRepository.delete(member);
    }

//    존재하는 멤버인지 확인 존재할 때 예외를 던짐
    private void verifiedMember(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if(optionalMember.isPresent()) {
            throw new BusinessLogicException(ExcepionCode.MEMBER_EXIST);
        }
    }
//    존재하는 멤버인지 확인. 존재하지 않을 때 예외를 던짐
    public Member existMember(long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.orElseThrow(() -> new BusinessLogicException(ExcepionCode.MEMBER_NOT_FOUND));
        return member;
    }
}
