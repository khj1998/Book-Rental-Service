package com.userservice.framework.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberInfoDto {
    private String id;
    private String name;
    private String password;
    private String email;
}
