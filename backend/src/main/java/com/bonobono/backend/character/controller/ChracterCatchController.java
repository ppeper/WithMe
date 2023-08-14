package com.bonobono.backend.character.controller;


import com.bonobono.backend.character.dto.catchCharacter.NowPositionRequestDto;
import com.bonobono.backend.character.dto.catchCharacter.OurChacracterWithSeaResponseDto;
import com.bonobono.backend.character.dto.catchCharacter.UserCharacterWithSeaRequestDto;
import com.bonobono.backend.character.service.OurCharacterService;
import com.bonobono.backend.character.service.UserCharacterService;
import com.bonobono.backend.dailymission.dto.QuizRequestDto;
import com.bonobono.backend.dailymission.dto.QuizeResponseDto;
import com.bonobono.backend.dailymission.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catch")
public class ChracterCatchController {

    private final OurCharacterService ourCharacterService;
    private final QuizService quizService;
    private final UserCharacterService userCharacterService;
    //요청받은 해변정보를 바탕으로 ourcharacter의 리스트와 그 위경도 값을 기준으로 랜덤으로 각 캐릭터의 위치들을 반환.
    // (이떄 response테이블에 각 캐릭터의 위치필드를 추가해야할지 화인 필요)
    @PostMapping("/list")
    public ResponseEntity<List<OurChacracterWithSeaResponseDto>> OurFindAll(@RequestBody NowPositionRequestDto nowPositionRequestDto) {
        List<OurChacracterWithSeaResponseDto> userChracterResponseDtoList = ourCharacterService.SeaOurFindList(nowPositionRequestDto);
        return ResponseEntity.ok(userChracterResponseDtoList);
    }

    //유저가 a캐릭터 범위에 들어왔으니, 문제달라고 요청하면, 캐릭터 잡기용 ox퀴즈 넘겨주기(get)
    @GetMapping("/ox")
    public ResponseEntity<QuizeResponseDto> oxQuiz() throws ParseException {
        QuizeResponseDto quizResponseDto = quizService.checkoxQuiz("map");
        return ResponseEntity.ok(quizResponseDto);
    }

    //정답 체크하고 맞으면 정답입니다반환
    @PostMapping("/ox/IsSuccess")
    public ResponseEntity<Boolean> oxcheckAnswer(@RequestBody QuizRequestDto quizRequestDto) {
        boolean result = quizService.oxIsSuccess("map",quizRequestDto);
        return ResponseEntity.ok(result);
    }

    //LocationOurCharacter의 id와 custom한 이름을 주면, userchar을 save()
    @PatchMapping("/UserCharacter/save")
    public ResponseEntity<?> save(@RequestBody UserCharacterWithSeaRequestDto requestDto) {
        userCharacterService.save(requestDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
