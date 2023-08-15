package com.bonobono.backend.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "토큰 재발급 요청 Dto 입니다.")
public class TokenRequestDto {

    @Schema(description = "엑세스토큰")
    private String accessToken;

    @Schema(description = "리프레시토큰")
    private String refreshToken;
}