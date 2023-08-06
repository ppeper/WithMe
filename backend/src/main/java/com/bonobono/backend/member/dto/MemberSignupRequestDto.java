package com.bonobono.backend.member.dto;

import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.enumtype.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class MemberSignupRequestDto {
    private String name;
    private String nickname;
    private String username;
    private String password;
    private String phoneNumber;

    public Member toEntity() {
        return Member.builder()
            .name(name)
            .nickname(nickname)
            .username(username)
            .password(password)
            .phoneNumber(phoneNumber)
            .role(Role.USER)
            .build();
    }
}
