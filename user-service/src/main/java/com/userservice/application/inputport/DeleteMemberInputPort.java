package com.userservice.application.inputport;

import com.userservice.application.outputport.MemberOutputPort;
import com.userservice.application.usecase.DeleteMemberUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeleteMemberInputPort implements DeleteMemberUseCase {
    private final MemberOutputPort memberOutputPort;

    @Override
    public void deleteMember(long memberNo) {
        log.info("Delete Member Id : {}",memberNo);
        memberOutputPort.deleteMember(memberNo);
    }
}
