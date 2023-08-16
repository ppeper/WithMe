package com.bonobono.backend.character.controller;


import com.bonobono.backend.character.dto.basket.CharacterMainUpdateRequestDto;
import com.bonobono.backend.character.dto.OurCharacterResponseDto;
import com.bonobono.backend.character.dto.UserChracterResponseDto;
import com.bonobono.backend.character.service.OurCharacterService;
import com.bonobono.backend.character.service.UserCharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/character")
public class CharacterController {

    private final UserCharacterService userCharacterService;

    //도감 잡은 동물들 보여주기
    @GetMapping("/user/list")
    public ResponseEntity<List<UserChracterResponseDto>> UserfindAll() {
        List<UserChracterResponseDto> userChracterResponseDtoList = userCharacterService.UserfindByList();
        return ResponseEntity.ok(userChracterResponseDtoList);
    }

    // 기다리고 있는 친구들 리스트만 보내기(ourcharacter에서 user가 아닌애들만 = 모바일에서 처리할지 여부확인 후 수정)
    @GetMapping("/our/list")
    public ResponseEntity<List<OurCharacterResponseDto>> OurfindAll() {
        List<OurCharacterResponseDto> ourCharacterResponseDtoList = userCharacterService.OurfindByList();
        return ResponseEntity.ok(ourCharacterResponseDtoList);
    }

    // 각 상세 캐릭터들을 보여줌(잡은 해변위치나 이런건 나중에)
    @GetMapping("/user/{character_id}")
    public ResponseEntity<UserChracterResponseDto> findById(@PathVariable Long character_id) {
        // @AuthenticationPrincipal 사용하기
        UserChracterResponseDto responseDto = userCharacterService.findById(character_id);
        return ResponseEntity.ok(responseDto);
    }

    // 커스텀 이름 바꿀 수 있도록 하기(put)
//    @PatchMapping("/user/name/{character_id}")
//    public ResponseEntity<Void> updateName(@PathVariable Long character_id, @RequestBody CharacterNameUpdateRequestDto memberRequestDto) {
//        userCharacterService.updateName(character_id, memberRequestDto);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    //대표캐릭터로 지정하기(put)
    //제일 처음 캐릭터는 default로 제공
    @PatchMapping("/user/main/{character_id}")
    public ResponseEntity<Void> updateMain(@PathVariable Long character_id, @RequestBody CharacterMainUpdateRequestDto memberRequestDto) {
        userCharacterService.updateMain(character_id, memberRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
