package com.userservice.framework.web.dto;

import com.userservice.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberOutputDto {
    private String id;
    private String name;
    private String password;
    private String email;
    private long point;

    public static MemberOutputDto mapToDto(Member member) {
        MemberOutputDto memberOutputDto = new MemberOutputDto();
        memberOutputDto.setId(member.getIdName().getId());
        memberOutputDto.setName(member.getIdName().getName());
        memberOutputDto.setPassword(member.getPassword().getPresentPWD());
        memberOutputDto.setEmail(member.getEmail().getAddress());
        memberOutputDto.setPoint(member.getPoint().getPointValue());

        return memberOutputDto;
    }
}
