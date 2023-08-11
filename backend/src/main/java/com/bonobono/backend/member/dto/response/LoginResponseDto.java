package com.bonobono.backend.member.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@Schema(description = "로그인정보 응답 Dto")
public class LoginResponseDto {

    private Long memberId;
    private TokenDto tokenDto;

    public LoginResponseDto(Long memberId, TokenDto tokenDto) {
        this.memberId = memberId;
        this.tokenDto = tokenDto;
    }

}
