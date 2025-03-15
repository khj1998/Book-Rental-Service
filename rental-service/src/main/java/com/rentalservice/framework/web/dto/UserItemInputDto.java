package com.rentalservice.framework.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserItemInputDto {
    public String userId;
    public String userName;
    public Integer itemId;
    public String itemTitle;
}
