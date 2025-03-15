package com.userservice.application.inputport;

import com.userservice.application.outputport.MemberOutputPort;
import com.userservice.application.usecase.InquiryMemberUseCase;
import com.userservice.domain.Member;
import com.userservice.framework.web.dto.MemberOutputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class InquiryMemberInputPort implements InquiryMemberUseCase {

    private final MemberOutputPort memberOutputPort;

    @Override
    public MemberOutputDto getMember(long memberNo) {
        log.info("Inquiry Member With Id {}",memberNo);
        Member loadMember = memberOutputPort.loadMember(memberNo);
        return MemberOutputDto.mapToDto(loadMember);
    }
}
