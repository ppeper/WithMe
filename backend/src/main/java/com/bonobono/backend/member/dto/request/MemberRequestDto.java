package com.bonobono.backend.member.dto.request;

import com.bonobono.backend.member.domain.Authority;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.enumtype.Provider;
import com.bonobono.backend.member.domain.enumtype.Role;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    private Long memberId;
    private String username;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;

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

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

}
