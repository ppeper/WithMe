package com.bonobono.backend.auth.oauth;

import com.bonobono.backend.auth.jwt.TokenProvider;
import com.bonobono.backend.member.domain.Authority;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.dto.request.MemberRequestDto;
import com.bonobono.backend.member.dto.response.TokenDto;
import com.bonobono.backend.member.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final ObjectMapper mapper;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        log.debug("Principal에서 꺼낸 OAuth2User = {}", oAuth2User);
        String email = oAuth2User.getAttribute("email");

        // 최초 로그인이라면 회원가입 처리
        Member member = memberRepository.findByUsername(email).orElse(null);

        // 토큰에 담을 정보
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json,charset=utf-8");

        PrintWriter writer = response.getWriter();
        MemberRequestDto memberDto = null;
        Map<String, Object> map = new HashMap<>();

        Set<Authority> role = member.getRole();

        if (member == null) {
            memberDto = new MemberRequestDto();
            memberDto.setUsername(email);
            memberDto.setRole(role);
            authentication = new UsernamePasswordAuthenticationToken(memberDto.getUsername(), null, memberDto.toAuthentication().getAuthorities());
            map.put("msg", "가입이 필요합니다.");
            map.put("email", memberDto.getUsername());
            map.put("isRegistered", false);
            map.put("member_id", -1);
        } else {
            memberDto = MemberRequestDto.of(member);
            authentication = new UsernamePasswordAuthenticationToken(memberDto.getUsername(), null, memberDto.toAuthentication().getAuthorities());
            response.setStatus(HttpStatus.OK.value());
            map.put("msg", "정상적으로 토큰을 발급합니다.");
            map.put("isRegistered", true);
            map.put("email", memberDto.getUsername());
            map.put("member_id", memberDto.getMemberId());
        }

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        map.put("grant_type", tokenDto.getGrantType());
        map.put("access_token", tokenDto.getAccessToken());
        map.put("refresh_token", tokenDto.getRefreshToken());
        map.put("access_token_expires_in", tokenDto.getAccessTokenExpiresIn());
        writer.append(mapper.writeValueAsString(map));

        writer.flush();

        log.info("토큰 발행 : {}", map.toString());

    }
}
