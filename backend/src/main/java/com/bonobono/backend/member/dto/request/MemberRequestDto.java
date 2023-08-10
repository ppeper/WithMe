package com.bonobono.backend.member.dto.request;

import com.bonobono.backend.member.domain.Authority;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.enumtype.Provider;

import java.io.Serializable;
import java.util.Set;

import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto implements Serializable {

    private Long memberId;
    private String username;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;
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

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    public static MemberRequestDto of(Member member) {
        
        if (member == null) {
            return null;
        }

        Set<Authority> role = member.getRole();

        return new MemberRequestDtoBuilder()
                .memberId(member.getId())
                .username(member.getUsername())
                .name(member.getName())
                .nickname(member.getNickname())
                .phoneNumber(member.getPhoneNumber())
                .role(role)
                .build();
    }

}
