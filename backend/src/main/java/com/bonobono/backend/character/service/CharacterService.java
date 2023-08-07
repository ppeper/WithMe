package com.bonobono.backend.character.service;

import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.character.dto.OurCharacterResponseDto;
import com.bonobono.backend.character.dto.UserChracterResponseDto;
import com.bonobono.backend.character.repository.OurCharacterRepository;
import com.bonobono.backend.character.repository.UserCharacterRepository;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CharacterService {

    private final UserCharacterRepository userCharacterRepository;
    private final OurCharacterRepository ourCharacterRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<UserChracterResponseDto> UserfindByList(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("해당 멤버가 없습니다. id =" + memberId));

        return userCharacterRepository.findByMemberId(memberId)
                    .stream()
                    .map(userCharacter->new UserChracterResponseDto(userCharacter,member))
                    .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OurCharacterResponseDto> OurfindByList(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("해당 멤버가 없습니다. id =" + memberId));

        return ourCharacterRepository.findNotLinkedOurCharactersByMember(memberId)
                .stream()
                .map(OurCharacterResponseDto::new)
                .collect(Collectors.toList());
    }

    public UserChracterResponseDto findById(Long character_id, Long memberId) {
        UserCharacter userCharacter = userCharacterRepository.findById(character_id)
                .orElseThrow(() -> new IllegalArgumentException("캐릭터가 없습니다 id ="+character_id));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("해당 멤버가 없습니다. id =" + memberId));
        return new UserChracterResponseDto(userCharacter, member);

    }



    //채팅도 사용자id넘겨줘서 구현하기


}
