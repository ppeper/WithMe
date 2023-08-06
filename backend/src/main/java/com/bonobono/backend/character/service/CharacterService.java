package com.bonobono.backend.character.service;

import com.bonobono.backend.character.domain.OurCharacter;
import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.character.dto.OurCharacterResponseDto;
import com.bonobono.backend.character.dto.UserChracterResponseDto;
import com.bonobono.backend.character.repository.OurCharacterRepository;
import com.bonobono.backend.character.repository.UserCharacterRepository;
import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.chatting.dto.ChatRoomResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CharacterService {

    private final UserCharacterRepository userCharacterRepository;
    private final OurCharacterRepository ourCharacterRepository;

    @Transactional(readOnly = true)
    public List<UserChracterResponseDto> UserfindByList(Long memberId) {
        return userCharacterRepository.findByMemberId(memberId)
                    .stream()
                    .map(UserChracterResponseDto::new)
                    .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OurCharacterResponseDto> OurfindByList(Long memberId) {
        return ourCharacterRepository.findNotLinkedOurCharactersByMember(memberId)
                .stream()
                .map(OurCharacterResponseDto::new)
                .collect(Collectors.toList());
    }
    //채팅도 사용자id넘겨줘서 구현하기


}
