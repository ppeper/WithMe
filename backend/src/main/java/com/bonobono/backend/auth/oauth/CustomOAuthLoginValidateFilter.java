package com.bonobono.backend.auth.oauth;

import com.bonobono.backend.auth.oauth.validate.GoogleTokenValidate;
import com.bonobono.backend.auth.oauth.validate.KakaoTokenValidate;
import com.bonobono.backend.auth.oauth.validate.NaverTokenValidator;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
@RequiredArgsConstructor
public class CustomOAuthLoginValidateFilter extends GenericFilter {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private final GoogleTokenValidate googleTokenValidate;
    private final KakaoTokenValidate kakaoTokenValidate;
    private final NaverTokenValidator naverTokenValidator;

    private final SimpleUrlAuthenticationSuccessHandler successHandler;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        try {

            boolean result = doFilter((HttpServletRequest) request, (HttpServletResponse) response);

            if (result) {
                successHandler.onAuthenticationSuccess((HttpServletRequest) request, (HttpServletResponse) response,
                        SecurityContextHolder.getContext().getAuthentication());
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            chain.doFilter(request, response);
        }
    }

    private boolean doFilter(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uri = request.getServletPath();
        if (!pathMatcher.match("/member/login/**", uri)) {
            return false;
        }

        String idToken = request.getParameter("code");

        if (Strings.isNullOrEmpty(idToken)) {
            // 토큰이 없는데 검사 요청을 하면
            return false;
        }

        log.info("Oauth token 검증 시작");
        int startIndex = uri.indexOf("login/") + "login/".length();
        int endIndex = uri.indexOf("/callback");
        String registrationId = uri.substring(startIndex, endIndex);
        SecurityContextHolder.getContext().getAuthentication();

        Map<String, Object> memberMap = null;

        try {
            log.info(registrationId);
            switch (registrationId) {
                // 클라이언트에게 받은 토큰을 통해 이메일 계정을 받아 맵에 채워서 반환
                case "google":
                    memberMap = googleTokenValidate.validate(idToken);
                    break;
                case "kakao":
                    log.info("카카오들어왔나?");
                    memberMap = kakaoTokenValidate.validate(idToken);
                    break;
                case "naver":
                    memberMap = naverTokenValidator.validate(idToken);
                    String email = request.getParameter("email");
                    memberMap.put("username", email);
                default:
                    break;
            }
        } catch (Exception e) {
            log.warn("토큰 검증 에러: {}", e.getMessage());
            e.printStackTrace();
        }

        if (memberMap == null && memberMap.size() == 0) {
            return false; // 검증 실패
        }

        OAuth2User oAuth2User = new DefaultOAuth2User(null, memberMap, "username");
        OAuth2AuthenticationToken oauthToken = new OAuth2AuthenticationToken(oAuth2User, null, registrationId);

        SecurityContextHolder.getContext().setAuthentication(oauthToken);

        return true;
    }
}
