package com.bonobono.backend.dailymission.controller;

import com.bonobono.backend.dailymission.dto.MiniGameRequestDto;
import com.bonobono.backend.dailymission.dto.MiniGameResponseDto;
import com.bonobono.backend.dailymission.service.MiniGameService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/minigame")
@RequiredArgsConstructor
public class MiniGameController {

    private final MiniGameService miniGameService;

    // minigame responseDto로 미니게임 문제와 답 넘겨주기(랜덤)
    //참여하기 버튼 누르면, 미니게임 이미 참여하면 참여하셨습니다를 반환
    // 미니게임 참여하지 않았으면, 미니게임 객체를 반환
    @GetMapping
    public ResponseEntity<MiniGameResponseDto> checkMiniGame() {
        MiniGameResponseDto miniGameResponseDto = miniGameService.checkMiniGame();
        return ResponseEntity.ok(miniGameResponseDto);
    }

    // 문제와 답을 주면 맞는지 여부를 넘겨주고, 경험치 UP
    @PostMapping("/IsSuccess")
    public ResponseEntity<Boolean> checkAnswer(@RequestBody MiniGameRequestDto miniGameRequestDto) {
        boolean result = miniGameService.IsSuccess(miniGameRequestDto);
        return ResponseEntity.ok(result);
    }



}
