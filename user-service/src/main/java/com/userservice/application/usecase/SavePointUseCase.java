package com.userservice.application.usecase;

import com.userservice.domain.vo.IdName;
import com.userservice.framework.web.dto.MemberOutputDto;

public interface SavePointUseCase {
    MemberOutputDto savePoint(IdName idName, Long point);
}
