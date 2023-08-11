package com.bonobono.backend.member.service;

import com.bonobono.backend.auth.jwt.TokenProvider;
import com.bonobono.backend.global.util.SecurityUtil;
import com.bonobono.backend.member.domain.Authority;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.Token;
import com.bonobono.backend.member.domain.enumtype.Role;
import com.bonobono.backend.member.dto.request.*;
import com.bonobono.backend.member.dto.response.MemberResponseDto;
import com.bonobono.backend.member.dto.response.TokenDto;
import com.bonobono.backend.global.exception.AppException;
import com.bonobono.backend.global.exception.ErrorCode;
import com.bonobono.backend.member.repository.AuthorityRepository;
import com.bonobono.backend.member.repository.MemberRepository;
import com.bonobono.backend.member.repository.TokenRepository;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthorityRepository authorityRepository;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;
    private final TokenRepository tokenRepository;

    /**
     * 회원가입
     */
    @Transactional
    public MemberResponseDto signup(MemberRequestDto request) {
        // 아이디 중복 검증
        memberRepository.findByUsername(request.getUsername())
            .ifPresent(member -> {
                throw new AppException(ErrorCode.USERNAME_DUPLICATED, "이미 존재하는 아이디입니다.");
            });
        
        // 닉네임 중복 검증
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
    public TokenDto login(MemberLoginRequestDto request) {
        // 아이디가 틀렸을 때
        Member member = memberRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> {
                throw new AppException(ErrorCode.USERNAME_NOTFOUND, "존재하지 않는 아이디입니다.");
            });
        
        // 비밀번호를 틀렸을 때
        if (!bCryptPasswordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "잘못된 비밀번호입니다.");
        }

        UsernamePasswordAuthenticationToken authenticationToken = request.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // refreshToken 저장
        Token refreshToken = Token.builder()
            .key(authentication.getName())
            .value(tokenDto.getRefreshToken())
            .fcmtoken(request.getFcmtoken())
            .build();

        tokenRepository.save(refreshToken);

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
        Token refreshToken = tokenRepository.findByKey(authentication.getName())
            .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // refresh token이 다르면
        if (!refreshToken.getValue().equals(request.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // refreshToken 업데이트
        Token newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        tokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }

    /**
     * 회원 정보 조회
     */
    @Transactional(readOnly = true)
    public MemberResponseDto myProfile() {
        return memberRepository.findById(SecurityUtil.getLoginMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    /**
     * 회원 정보 수정 (아이디 수정 불가)
     */
    @Transactional
    public void updateMyInfo(MemberUpdateRequestDto dto) {
        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        member.updateMember(dto);
    }

    /**
     * 비밀번호 수정
     */
    @Transactional
    public void passwordChange(PasswordChangeRequestDto request) {
        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        member.changePassword(request, bCryptPasswordEncoder);
    }

    /**
     * 로그아웃
     */
    @Transactional
    public void logout(HttpServletRequest request) {
        // accessToken을 다른 Table에 등록해서 해당 테이블에 accessToken이 존재하면 로그아웃된 사용자로 인식
        // 나중에 구현해야할 필요가 있음

        // refreshToken 삭제
        tokenRepository.deleteByKey(String.valueOf(SecurityUtil.getLoginMemberId()))
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    /**
     * 회원탈퇴
     */
    @Transactional
    public void deleteMember() {
        Long loginMemberId = SecurityUtil.getLoginMemberId();
        if (loginMemberId == null) {
            throw new RuntimeException("로그인 유저 정보가 없습니다.");
        }
        memberRepository.deleteById(loginMemberId);
    }

}