package com.userservice.application.usecase;

import com.userservice.framework.web.dto.MemberOutputDto;

public interface InquiryMemberUseCase {
    MemberOutputDto getMember(long memberNo);
}
