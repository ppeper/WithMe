package com.bonobono.backend.location.controller;

import com.bonobono.backend.location.dto.req.RewardRequestDto;
import com.bonobono.backend.location.dto.res.RewardListResponseDto;
import com.bonobono.backend.location.service.RewardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "reward", description = "리워드")
@RequiredArgsConstructor
@RestController
@RequestMapping("/reward")
public class RewardController {

    private final RewardService rewardService;

    @Operation(summary = "장소별 리워드 순위 불러오기")
    @GetMapping("/{locationId}")
    public ResponseEntity<List<RewardListResponseDto>> findAll(@PathVariable Long locationId){
        List<RewardListResponseDto> responseDto = rewardService.findAllByLocation(locationId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "리워드 횟수 증가 및 생성", description = "리워드 횟수 증가, 처음이면 생성")
    @PostMapping("")
    public ResponseEntity<Void> updateReward(@RequestBody RewardRequestDto requestDto){
        rewardService.updateRewardCnt(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
