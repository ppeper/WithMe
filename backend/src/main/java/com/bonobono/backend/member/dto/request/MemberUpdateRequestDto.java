package com.bonobono.backend.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberUpdateRequestDto {

    private String nickname;
    private String password;
    private String phoneNumber;

}
