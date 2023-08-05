//package com.bonobono.backend.member.controller;
//
//import com.bonobono.backend.member.dto.request.MemberLoginRequestDto;
//import com.bonobono.backend.member.dto.request.MemberSignupRequestDto;
//import com.bonobono.backend.member.service.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/members")
//public class MemberController {
//
//    private final MemberService memberService;
//
//    @PostMapping("/signup")
//    public ResponseEntity<String> signup(@RequestBody MemberSignupRequestDto request) {
//        memberService.signup(request.getName(), request.getNickname(), request.getUsername(),
//            request.getPassword(), request.getPhoneNumber());
//        return ResponseEntity.ok().body("회원가입 성공");
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody MemberLoginRequestDto request) {
//        String token = memberService.login(request.getUsername(), request.getPassword());
//        return ResponseEntity.ok().body(token);
//    }
//}
