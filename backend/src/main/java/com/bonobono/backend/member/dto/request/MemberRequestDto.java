package com.bonobono.backend.member.dto.request;

import com.bonobono.backend.member.domain.Authority;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.ProfileImg;
import com.bonobono.backend.member.domain.enumtype.Provider;

import java.io.Serializable;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "회원정보 요청 Dto 입니다.")
public class MemberRequestDto implements Serializable {

    private Long memberId;

    @Schema(description = "아이디")
    private String username;

    @Schema(description = "비밀번호")
    private String password;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "닉네임")
    private String nickname;

    @Schema(description = "휴대폰번호")
    private String phoneNumber;

    @Schema(description = "권한")
    private Set<Authority> role;

    @Builder
    public MemberRequestDto(Long memberId){

        this.memberId = memberId;
    }

    public Member toMember(BCryptPasswordEncoder passwordEncoder, Set<Authority> role) {

        return Member.builder()
            .username(username)
            .password(passwordEncoder.encode(password))
            .name(name)
            .nickname(nickname)
            .phoneNumber(phoneNumber)
            .role(role)
            .provider(Provider.EMPTY)
            .build();
    }
}