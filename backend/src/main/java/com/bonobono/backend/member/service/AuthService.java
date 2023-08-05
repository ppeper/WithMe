package com.bonobono.backend.member.service;

import com.bonobono.backend.global.jwt.TokenProvider;
import com.bonobono.backend.member.domain.Authority;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.RefreshToken;
import com.bonobono.backend.member.domain.enumtype.Role;
import com.bonobono.backend.member.dto.request.MemberRequestDto;
import com.bonobono.backend.member.dto.request.TokenRequestDto;
import com.bonobono.backend.member.dto.response.MemberResponseDto;
import com.bonobono.backend.member.dto.response.TokenDto;
import com.bonobono.backend.member.exception.AppException;
import com.bonobono.backend.member.exception.ErrorCode;
import com.bonobono.backend.member.repository.AuthorityRepository;
import com.bonobono.backend.member.repository.MemberRepository;
import com.bonobono.backend.member.repository.RefreshTokenRepository;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthorityRepository authorityRepository;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * 회원가입
     */
    @Transactional
    public MemberResponseDto signup(MemberRequestDto request) {
        // 아이디 중복 회원 검증
        memberRepository.findByUsername(request.getUsername())
            .ifPresent(member -> {
                throw new AppException(ErrorCode.USERNAME_DUPLICATED, "이미 존재하는 아이디입니다.");
            });

        memberRepository.findByNickname(request.getNickname())
            .ifPresent(member -> {
                throw new AppException(ErrorCode.NICKNAME_DUPLICATED, "이미 존재하는 닉네임입니다.");
            });

        Authority authority = authorityRepository
            .findByRole(Role.USER)
            .orElseThrow(() -> new RuntimeException("권한 정보가 없습니다."));

        Set<Authority> set = new HashSet<>();
        set.add(authority);

        Member member = request.toMember(bCryptPasswordEncoder, set);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    /**
     * 로그인
     */
    @Transactional
    public TokenDto login(MemberRequestDto request) {
        UsernamePasswordAuthenticationToken authenticationToken = request.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // refreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
            .key(authentication.getName())
            .value(tokenDto.getRefreshToken())
            .build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }

    /**
     * 토큰 재발급
     */
    @Transactional
    public TokenDto reissue(TokenRequestDto request) {
        // refresh Token 검증
        if (!tokenProvider.validateToken(request.getRefreshToken())) {
            throw new RuntimeException("Refresh Token이 유효하지 않습니다.");
        }

        // access Token에서 Authentication 객체 가져오기
        Authentication authentication = tokenProvider.getAuthentication(request.getAccessToken());

        // DB에서 member_id를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
            .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // refresh token이 다르면
        if (!refreshToken.getValue().equals(request.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // refreshToken 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }


}
