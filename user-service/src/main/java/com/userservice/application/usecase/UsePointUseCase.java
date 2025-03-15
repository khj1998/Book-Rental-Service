package com.userservice.application.usecase;

import com.userservice.domain.vo.IdName;
import com.userservice.framework.web.dto.MemberOutputDto;

public interface UsePointUseCase {
    MemberOutputDto usePoint(IdName idName, long point) throws Exception;
}
