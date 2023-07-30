package com.bonobono.backend.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberLoginRequest {

    private String accountId;
    private String password;

}
