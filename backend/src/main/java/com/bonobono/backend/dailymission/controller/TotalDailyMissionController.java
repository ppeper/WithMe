package com.bonobono.backend.dailymission.controller;


import com.bonobono.backend.dailymission.dto.TotalDailyMissionResponseDto;
import com.bonobono.backend.dailymission.service.AttendanceService;
import com.bonobono.backend.dailymission.service.TotalDailyMissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/dailymission")
@RestController
@RequiredArgsConstructor
public class TotalDailyMissionController {

    /** 처음 페이지를 들어가면 보이는 달성률들 */
    private final TotalDailyMissionService totalDailyMissionService;


    // date를 체크해서 한달 중 몇%를 했는지 반환해주는 함수
    @GetMapping
    public ResponseEntity<TotalDailyMissionResponseDto> attendancePercentage() {
        TotalDailyMissionResponseDto result = totalDailyMissionService.AttendanceAndTotalPercentage();
        return ResponseEntity.ok(result);
    }

}
