package com.bonobono.backend.member.controller;

import com.bonobono.backend.global.exception.AppException;
import com.bonobono.backend.global.exception.ErrorCode;
import com.bonobono.backend.member.dto.request.MemberNicknameRequestDto;
import com.bonobono.backend.member.dto.request.MemberRequestDto;
import com.bonobono.backend.member.dto.request.MemberUpdateRequestDto;
import com.bonobono.backend.member.dto.request.MemberUsernameRequestDto;
import com.bonobono.backend.member.dto.request.TokenRequestDto;
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

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(memberService.signup(memberRequestDto));
    }

    @PostMapping("/username")
    public String username(@RequestBody MemberUsernameRequestDto request) {
        memberRepository.findByUsername(request.getUsername())
            .ifPresent(member -> {
                throw new AppException(ErrorCode.USERNAME_DUPLICATED, "이미 존재하는 아이디입니다.");
            });

        return "SUCCESS";
    }

    @PostMapping("/nickname")
    public String nickname(@RequestBody MemberNicknameRequestDto request) {
        memberRepository.findByNickname(request.getNickname())
            .ifPresent(member -> {
                throw new AppException(ErrorCode.NICKNAME_DUPLICATED, "이미 존재하는 닉네임입니다.");
            });

        return "SUCCESS";
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(memberService.login(memberRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(memberService.reissue(tokenRequestDto));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public ResponseEntity<MemberResponseDto> myProfile() {
        return ResponseEntity.ok(memberService.myProfile());
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/update")
    public ResponseEntity<MemberResponseDto> updateMyInfo(@RequestBody MemberUpdateRequestDto dto) {
        memberService.updateMyInfo(dto);
        return ResponseEntity.ok(memberService.myProfile());
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/profile")
    public ResponseEntity<String> deleteMember(HttpServletRequest request) {
        memberService.logout(request);
        memberService.deleteMember();
        return new ResponseEntity<>("회원 탈퇴 성공", HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        memberService.logout(request);
        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }
}