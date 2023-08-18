package com.bonobono.backend.member.dto.response;

import com.bonobono.backend.member.domain.Authority;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.ProfileImg;
import com.bonobono.backend.member.domain.enumtype.Role;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "회원정보 응답 Dto 입니다.")
public class MemberResponseDto {

    @Schema(description = "아이디")
    private String username;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "닉네임")
    private String nickname;

    @Schema(description = "휴대폰번호")
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