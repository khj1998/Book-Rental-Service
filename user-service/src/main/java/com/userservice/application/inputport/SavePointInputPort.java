package com.userservice.application.inputport;

import com.userservice.application.outputport.MemberOutputPort;
import com.userservice.application.usecase.SavePointUseCase;
import com.userservice.domain.Member;
import com.userservice.domain.vo.IdName;
import com.userservice.framework.web.dto.MemberOutputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SavePointInputPort implements SavePointUseCase {

    private final MemberOutputPort memberOutputPort;

    @Override
    public MemberOutputDto savePoint(IdName idName, Long point) {
        Member member = memberOutputPort.loadMemberByIdName(idName);
        log.info("Member Id {} 's Point has been added : +{}",idName.getId(),point);
        member.savePoint(point);
        return MemberOutputDto.mapToDto(member);
    }
}
