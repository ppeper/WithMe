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
@AllArgsConstructor
@Builder
@Schema(description = "토큰정보 응답 Dto 입니다.")
public class TokenDto {

    @Schema(description = "권한타입")
    private String grantType;

    @Schema(description = "엑세스토큰")
    private String accessToken;

    @Schema(description = "리프레시토큰")
    private String refreshToken;

    @Schema(description = "엑세스토큰만료시간")
    private Long accessTokenExpiresIn;
}