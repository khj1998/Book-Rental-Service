package com.userservice.application.inputport;

import com.userservice.application.outputport.MemberOutputPort;
import com.userservice.application.usecase.AddMemberUseCase;
import com.userservice.domain.Member;
import com.userservice.domain.vo.Email;
import com.userservice.domain.vo.IdName;
import com.userservice.domain.vo.PassWord;
import com.userservice.framework.web.dto.MemberInfoDto;
import com.userservice.framework.web.dto.MemberOutputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AddMemberInputPort implements AddMemberUseCase {

    private final MemberOutputPort memberOutputPort;

    @Override
    public MemberOutputDto addMember(MemberInfoDto memberInfoDto) {
        IdName idName = new IdName(memberInfoDto.getId(),memberInfoDto.getName());
        PassWord passWord = new PassWord(memberInfoDto.getPassword(),memberInfoDto.getPassword());
        Email email = new Email(memberInfoDto.getEmail());
        Member addedMember = Member.registerMember(idName,passWord,email);

        log.info("Create New Member From Dto : {}", memberInfoDto);
        Member savedMember = memberOutputPort.saveMember(addedMember);

        return MemberOutputDto.mapToDto(savedMember);
    }
}
