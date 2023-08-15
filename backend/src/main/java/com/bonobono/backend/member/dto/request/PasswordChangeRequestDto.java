package com.bonobono.backend.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "비밀번호 변경 요청 Dto 입니다.")
public class PasswordChangeRequestDto {

    @Schema(description = "현재 비밀번호")
    String password;

    @Schema(description = "새 비밀번호")
    String newPassword;
}