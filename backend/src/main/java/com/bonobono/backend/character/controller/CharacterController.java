package com.bonobono.backend.character.controller;


import com.bonobono.backend.character.domain.OurCharacter;
import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.character.dto.OurCharacterResponseDto;
import com.bonobono.backend.character.dto.UserChracterResponseDto;
import com.bonobono.backend.character.service.CharacterService;
import com.bonobono.backend.chatting.dto.ChatRoomResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/character")
public class CharacterController {

    private final CharacterService characterService;

    //도감 잡은 동물들 보여주기
    @GetMapping("/user/list")
    public ResponseEntity<List<UserChracterResponseDto>> UserfindAll(@RequestParam Long memberId) {
        List<UserChracterResponseDto> userChracterResponseDtoList = characterService.UserfindByList(memberId);
        return ResponseEntity.ok(userChracterResponseDtoList);
    }

    // 기다리고 있는 친구들 리스트만 보내기??(ourcharacter에서 user가 아닌애들만)
    @GetMapping("/our/list")
    public ResponseEntity<List<OurCharacterResponseDto>> OurfindAll(@RequestParam Long memberId) {
        List<OurCharacterResponseDto> ourCharacterResponseDtoList = characterService.OurfindByList(memberId);
        return ResponseEntity.ok(ourCharacterResponseDtoList);
    }

    // 각 상세 캐릭터들을 보여줌(잡은 해변위치나 이런건 나중에)


    // 커스텀 이름 바꿀 수 있도록 하기(put)

    //대표캐릭터로 지정하기



}
