package com.bonobono.backend.member.dto.response;


import com.bonobono.backend.member.domain.Authority;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Builder
@Schema(description = "로그인정보 응답 Dto 입니다.")
public class LoginResponseDto {

    private Long memberId;

    private TokenDto tokenDto;

    private Set<Authority> role;

    public LoginResponseDto(Long memberId, TokenDto tokenDto, Set<Authority> role) {

        this.memberId = memberId;
        this.tokenDto = tokenDto;
        this.role = role;
    }
}