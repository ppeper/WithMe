package com.bonobono.backend.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "닉네임 중복검사 요청 Dto 입니다.")
public class MemberNicknameRequestDto {

    @Schema(description = "닉네임")
    private String nickname;
}