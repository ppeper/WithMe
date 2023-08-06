package com.bonobono.backend.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberLoginRequestDto {

    private String username;
    private String password;

}
