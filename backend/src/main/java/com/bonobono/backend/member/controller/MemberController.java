package com.bonobono.backend.member.controller;

import com.bonobono.backend.member.dto.MemberLoginRequest;
import com.bonobono.backend.member.dto.MemberSignupRequest;
import com.bonobono.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberSignupRequest request) {
        memberService.signup(request.getName(), request.getNickname(), request.getAccountId(),
            request.getPassword(), request.getPhoneNumber());
        return ResponseEntity.ok().body("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberLoginRequest request) {
        String token = memberService.login(request.getAccountId(), request.getPassword());
        return ResponseEntity.ok().body(token);
    }
}
