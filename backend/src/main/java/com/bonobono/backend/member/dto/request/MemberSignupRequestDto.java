package com.bonobono.backend.member.dto.request;

import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.enumtype.Provider;
import com.bonobono.backend.member.domain.enumtype.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class MemberSignupRequestDto {
    private String name;
    private String nickname;
    private String username;
    private String password;
    private String phoneNumber;
}
