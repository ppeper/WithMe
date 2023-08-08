package com.bonobono.backend.member.dto.response;

import com.bonobono.backend.member.domain.Authority;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.enumtype.Role;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {

    private String username;
    private String name;
    private String nickname;
    private String phoneNumber;
    private Set<Authority> authoritySet;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(
            member.getUsername(),
            member.getName(),
            member.getNickname(),
            member.getPhoneNumber(),
            member.getRole()
        );
    }

}
