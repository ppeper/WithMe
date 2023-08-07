package com.bonobono.backend.dailymission.controller;

import com.bonobono.backend.dailymission.service.AttendanceService;
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

    //이미 출석했는지 확인
    @PostMapping
    public ResponseEntity<Void> check(@RequestParam Long memberId) {
        //시큐리티를 사용한다는 가정 @AuthenticationPrincipal Member member
        Boolean IsCheck = attendanceService.check(memberId);
        return IsCheck ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



}
