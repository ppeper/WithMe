package com.bonobono.backend.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@AllArgsConstructor
@Getter
@Schema(description = "회원 로그인 요청 Dto 입니다.")
public class MemberLoginRequestDto {

    private String fcmtoken;

    @Schema(description = "아이디")
    private String username;

    @Schema(description = "비밀번호")
    private String password;

    public UsernamePasswordAuthenticationToken toAuthentication() {

        return new UsernamePasswordAuthenticationToken(username, password);
    }
}