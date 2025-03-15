package com.userservice.application.inputport;

import com.userservice.application.outputport.MemberOutputPort;
import com.userservice.application.usecase.UsePointUseCase;
import com.userservice.domain.Member;
import com.userservice.domain.vo.IdName;
import com.userservice.framework.web.dto.MemberOutputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UsePointInputPort implements UsePointUseCase {

    private final MemberOutputPort memberOutputPort;

    @Override
    public MemberOutputDto usePoint(IdName idName, long point) {
        Member loadedMember = memberOutputPort.loadMemberByIdName(idName);
        log.info("Member Id {} has used {} point",idName.getId(),point);
        loadedMember.usePoint(point);
        return MemberOutputDto.mapToDto(loadedMember);
    }
}
