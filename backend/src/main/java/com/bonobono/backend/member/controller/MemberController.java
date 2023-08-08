package com.bonobono.backend.member.controller;

import com.bonobono.backend.global.exception.AppException;
import com.bonobono.backend.global.exception.ErrorCode;
import com.bonobono.backend.member.dto.request.*;
import com.bonobono.backend.member.dto.response.MemberResponseDto;
import com.bonobono.backend.member.dto.response.TokenDto;
import com.bonobono.backend.member.repository.MemberRepository;
import com.bonobono.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(memberService.signup(memberRequestDto));
    }

    /**
     * 아이디 중복체크
     */
    @PostMapping("/username")
    public String username(@RequestBody MemberUsernameRequestDto request) {
        memberRepository.findByUsername(request.getUsername())
            .ifPresent(member -> {
                throw new AppException(ErrorCode.USERNAME_DUPLICATED, "이미 존재하는 아이디입니다.");
            });

        return "SUCCESS";
    }

    /**
     * 닉네임 중복체크
     */
    @PostMapping("/nickname")
    public String nickname(@RequestBody MemberNicknameRequestDto request) {
        memberRepository.findByNickname(request.getNickname())
            .ifPresent(member -> {
                throw new AppException(ErrorCode.NICKNAME_DUPLICATED, "이미 존재하는 닉네임입니다.");
            });

        return "SUCCESS";
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(memberService.login(memberRequestDto));
    }

    /**
     * 토큰 재발급
     */
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(memberService.reissue(tokenRequestDto));
    }

    /**
     * 마이페이지 내 정보 조회
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public ResponseEntity<MemberResponseDto> myProfile() {
        return ResponseEntity.ok(memberService.myProfile());
    }

    /**
     * 회원정보 수정
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/update")
    public ResponseEntity<MemberResponseDto> updateMyInfo(@RequestBody MemberUpdateRequestDto dto) {
        memberService.updateMyInfo(dto);
        return ResponseEntity.ok(memberService.myProfile());
    }

    /**
     * 비밀번호 변경
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/password")
    public ResponseEntity<MemberResponseDto> passwordChange(@RequestBody PasswordChangeRequestDto dto) {
        memberService.passwordChange(dto);
        return ResponseEntity.ok(memberService.myProfile());
    }

    /**
     * 회원탈퇴
     */
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/profile")
    public ResponseEntity<String> deleteMember(HttpServletRequest request) {
        memberService.logout(request);
        memberService.deleteMember();
        return new ResponseEntity<>("회원 탈퇴 성공", HttpStatus.OK);
    }

    /**
     * 로그아웃
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        memberService.logout(request);
        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }
}