package com.bonobono.backend.dailymission.controller;

import com.bonobono.backend.dailymission.service.AttendanceService;
import com.bonobono.backend.global.util.SecurityUtil;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final MemberRepository memberRepository;

    //이미 출석했는지 확인
    @GetMapping
    public ResponseEntity<Void> check() {
        //시큐리티를 사용한다는 가정 @AuthenticationPrincipal Member member
        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        Boolean IsCheck = attendanceService.check(member.getId());
        return IsCheck ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



}
