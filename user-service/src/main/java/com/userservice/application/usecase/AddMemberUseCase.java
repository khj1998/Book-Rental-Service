package com.userservice.application.usecase;

import com.userservice.framework.web.dto.MemberInfoDto;
import com.userservice.framework.web.dto.MemberOutputDto;

public interface AddMemberUseCase {
    MemberOutputDto addMember(MemberInfoDto memberInputDto);
}
