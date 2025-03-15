package com.userservice.framework.jpaadapter;

import com.userservice.application.outputport.MemberOutputPort;
import com.userservice.domain.Member;
import com.userservice.domain.vo.IdName;
import com.userservice.framework.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberJpaAdapter implements MemberOutputPort {

    private final MemberRepository memberRepository;

    @Override
    public Member loadMember(long memberNo) {
        return memberRepository.findById(memberNo)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND.value(),"member not found"));
    }

    @Override
    public Member loadMemberByIdName(IdName idName) {
        return memberRepository.findMemberByIdName(idName)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND.value(),"member not found"));
    }

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void deleteMember(long memberNo) {
        memberRepository.deleteById(memberNo);
    }
}
