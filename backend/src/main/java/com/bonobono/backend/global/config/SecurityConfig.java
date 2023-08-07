package com.bonobono.backend.global.config;

import com.bonobono.backend.auth.jwt.*;
import com.bonobono.backend.auth.oauth.CustomOAuthLoginValidateFilter;
import com.bonobono.backend.auth.oauth.OAuthSuccessHandler;
import com.bonobono.backend.auth.oauth.validate.GoogleTokenValidate;
import com.bonobono.backend.auth.oauth.validate.KakaoTokenValidate;
import com.bonobono.backend.auth.oauth.validate.NaverTokenValidator;
import com.bonobono.backend.member.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
//    private final JdbcTemplate jdbcTemplate;
    private final TokenRepository tokenRepository;
    private final OAuth2UserService oAuth2UserService;
    private final OAuthSuccessHandler successHandler;
    private final GoogleTokenValidate googleTokenValidate;
    private final KakaoTokenValidate kakaoTokenValidate;
    private final NaverTokenValidator naverTokenValidator;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
        throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)
            .and()
            .addFilterBefore(new JwtFilter(tokenProvider, tokenRepository), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new CustomOAuthLoginValidateFilter(googleTokenValidate, kakaoTokenValidate, naverTokenValidator, successHandler),
                    JwtFilter.class)
            .apply(new JwtSecurityConfig(tokenProvider, tokenRepository));

        return http.build();
    }

}
