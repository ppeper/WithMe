package com.bonobono.backend.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "회원정보 수정 요청 Dto 입니다.")
public class MemberUpdateRequestDto {

    @Schema(description = "이름")
    private String name;

    @Schema(description = "닉네임")
    private String nickname;

    @Schema(description = "휴대폰번호")
    private String phoneNumber;
}