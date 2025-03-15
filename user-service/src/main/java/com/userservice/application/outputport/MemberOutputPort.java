package com.userservice.application.outputport;

import com.userservice.domain.Member;
import com.userservice.domain.vo.IdName;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberOutputPort {
    Member loadMember(long memberNo);
    Member loadMemberByIdName(IdName idName);
    Member saveMember(Member member);
    void deleteMember(long memberNo);
}
