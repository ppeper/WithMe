package com.bonobono.backend.dailymission.controller;

import com.bonobono.backend.dailymission.dto.AttendanceDto;
import com.bonobono.backend.dailymission.service.AttendanceService;
import com.bonobono.backend.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    //이미 출석했는지 확인
    @PostMapping
    public ResponseEntity<Void> check(@AuthenticationPrincipal Member member) {
        //시큐리티를 사용한다는 가정
        AttendanceDto attendanceDto = new AttendanceDto(member.getId());
        Boolean IsCheck = attendanceService.check(attendanceDto);
        return IsCheck ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // date를 체크해서 한달 중 몇%를 했는지 반환해주는 함수
    @GetMapping("/percentage")
    public int attendancePercentage(@AuthenticationPrincipal Member member) {
        AttendanceDto attendanceDto = new AttendanceDto(member.getId());
        int result = attendanceService.AttendancePercentage(attendanceDto);
        return result;
    }

}
