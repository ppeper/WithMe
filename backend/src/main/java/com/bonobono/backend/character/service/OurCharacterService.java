package com.bonobono.backend.character.service;

import com.bonobono.backend.character.dto.OurCharacterResponseDto;
import com.bonobono.backend.character.dto.catchCharacter.NowPositionRequestDto;
import com.bonobono.backend.character.dto.catchCharacter.OurChacracterWithSeaResponseDto;
import com.bonobono.backend.character.repository.OurCharacterRepository;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OurCharacterService {

    private final MemberRepository memberRepository;
    private final OurCharacterRepository ourCharacterRepository;


    public List<OurChacracterWithSeaResponseDto> SeaOurFindList(NowPositionRequestDto nowPositionRequestDto) {
        //요청받은 해변정보를 바탕으로 ourcharacter의 리스트와 그 위경도 값을 기준으로 랜덤으로 각 캐릭터의 위치들을 반환.
        
    }
}
