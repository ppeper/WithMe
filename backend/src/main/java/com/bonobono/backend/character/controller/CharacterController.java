package com.bonobono.backend.character.controller;


import com.bonobono.backend.character.dto.CharacterMainUpdateRequestDto;
import com.bonobono.backend.character.dto.OurCharacterResponseDto;
import com.bonobono.backend.character.dto.CharacterNameUpdateRequestDto;
import com.bonobono.backend.character.dto.UserChracterResponseDto;
import com.bonobono.backend.character.service.OurCharacterService;
import com.bonobono.backend.character.service.UserCharacterService;
import com.bonobono.backend.member.dto.MemberRequestDto;
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
    private final OurCharacterService ourCharacterService;

    //도감 잡은 동물들 보여주기
    @GetMapping("/user/list")
    public ResponseEntity<List<UserChracterResponseDto>> UserfindAll(@RequestParam Long memberId) {
        List<UserChracterResponseDto> userChracterResponseDtoList = userCharacterService.UserfindByList(memberId);
        return ResponseEntity.ok(userChracterResponseDtoList);
    }

    // 기다리고 있는 친구들 리스트만 보내기(ourcharacter에서 user가 아닌애들만 = 모바일에서 처리할지 여부확인 후 수정)
    @GetMapping("/our/list")
    public ResponseEntity<List<OurCharacterResponseDto>> OurfindAll(@RequestParam Long memberId) {
        List<OurCharacterResponseDto> ourCharacterResponseDtoList = ourCharacterService.OurfindByList(memberId);
        return ResponseEntity.ok(ourCharacterResponseDtoList);
    }

    // 각 상세 캐릭터들을 보여줌(잡은 해변위치나 이런건 나중에)
    @PostMapping("/user/{character_id}")
    public ResponseEntity<UserChracterResponseDto> findById(@PathVariable Long character_id, @RequestBody MemberRequestDto memberRequestDto) {
        // @AuthenticationPrincipal 사용하기
        UserChracterResponseDto responseDto = userCharacterService.findById(character_id, memberRequestDto.getMemberId());
        return ResponseEntity.ok(responseDto);
    }

    // 커스텀 이름 바꿀 수 있도록 하기(put)
    @PatchMapping("/user/name/{character_id}")
    public ResponseEntity<Void> updateName(@PathVariable Long character_id, @RequestBody CharacterNameUpdateRequestDto memberRequestDto) {
        userCharacterService.updateName(character_id, memberRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //대표캐릭터로 지정하기(put)
    //제일 처음 캐릭터는 default로 제공
    @PatchMapping("/user/main/{character_id}")
    public ResponseEntity<Void> updateMain(@PathVariable Long character_id, @RequestBody CharacterMainUpdateRequestDto memberRequestDto) {
        userCharacterService.updateMain(character_id, memberRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //캐릭터의 레벨별로 진화할 수 있도록??10개 캐릭터?? 1인 애들만 잡을 수 있고, 레벨 업은 캐릭터하면 바뀌는 걸로(수정되는것)(PUT)


    //OX퀴즈는 캐릭터를 잡을 때 하는 거 아닌지? 똑같이 가져다 씀(다른 로직)
    

}
