package com.userservice.domain.event;

import com.userservice.domain.vo.IdName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PointUseCommand {
    private IdName idName;
    private long point;
}
