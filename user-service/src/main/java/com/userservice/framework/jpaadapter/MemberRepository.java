package com.userservice.framework.jpaadapter;

import com.userservice.domain.Member;
import com.userservice.domain.vo.IdName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findMemberByIdName(IdName idName);
}
