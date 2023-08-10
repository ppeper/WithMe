package com.bonobono.backend.dailymission.controller;

import com.bonobono.backend.dailymission.dto.MiniGameRequestDto;
import com.bonobono.backend.dailymission.dto.QuizRequestDto;
import com.bonobono.backend.dailymission.dto.QuizeResponseDto;
import com.bonobono.backend.dailymission.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/quiz")
@RestController
@RequiredArgsConstructor
public class MyQuizController {
    private final QuizService quizService;

    //4지선다 문제(참여했다면, 다시 참여못하게)
    @GetMapping("/four_quiz")
    public ResponseEntity<QuizeResponseDto> fourQuiz(@RequestParam Long memberId) throws ParseException {
        QuizeResponseDto quizResponseDto = quizService.checkfourQuiz(memberId);
        return ResponseEntity.ok(quizResponseDto);
    }
    //ox 문제(참여했다면, 다시 참여못하게)
    @GetMapping("/ox")
    public ResponseEntity<QuizeResponseDto> oxQuiz(@RequestParam Long memberId) throws ParseException {
        QuizeResponseDto quizResponseDto = quizService.checkoxQuiz("quiz",memberId);
        return ResponseEntity.ok(quizResponseDto);
    }

    // 버튼을 누르고 정답 넣으면 체크(MAP에 넣고 체크하기) 경험치 UP
    @PostMapping("/four_quiz/IsSuccess")
    public ResponseEntity<Boolean> fourcheckAnswer(@RequestBody QuizRequestDto quizRequestDto) {
        boolean result = quizService.FourIsSuccess(quizRequestDto);
        return ResponseEntity.ok(result);
    }

    // 버튼을 누르고 정답 넣으면 체크(MAP에 넣고 체크하기) 경험치 UP
    @PostMapping("/ox/IsSuccess")
    public ResponseEntity<Boolean> oxcheckAnswer(@RequestBody QuizRequestDto quizRequestDto) {
        boolean result = quizService.oxIsSuccess("quiz",quizRequestDto);
        return ResponseEntity.ok(result);
    }






}
