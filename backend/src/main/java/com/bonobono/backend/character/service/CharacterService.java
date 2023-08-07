package com.bonobono.backend.character.service;

import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.character.dto.CharacterMainUpdateRequestDto;
import com.bonobono.backend.character.dto.OurCharacterResponseDto;
import com.bonobono.backend.character.dto.CharacterNameUpdateRequestDto;
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

        return userCharacterRepository.findByMemberId(member.getId())
                    .stream()
                    .map(UserChracterResponseDto::new)
                    .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OurCharacterResponseDto> OurfindByList(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("해당 멤버가 없습니다. id =" + memberId));

        return ourCharacterRepository.findNotLinkedOurCharactersByMember(member.getId())
                .stream()
                .map(OurCharacterResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserChracterResponseDto findById(Long character_id, Long memberId) {
        UserCharacter userCharacter = userCharacterRepository.findByMemberIdAndId(memberId,character_id)
                .orElseThrow(() -> new IllegalArgumentException("유저에게 캐릭터가 없습니다 id ="+character_id));
        return new UserChracterResponseDto(userCharacter);
    }

    @Transactional
    public void updateName(Long character_id, CharacterNameUpdateRequestDto memberRequestDto) {
        UserCharacter userCharacter = userCharacterRepository.findByMemberIdAndId(memberRequestDto.getMemberId(),character_id)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저 캐릭터가 없습니다 id:"+character_id));
        userCharacter.updateName(memberRequestDto.getCustom_name());
    }

    @Transactional
    public void updateMain(Long character_id, CharacterMainUpdateRequestDto memberRequestDto) {
        UserCharacter userCharacter = userCharacterRepository.findByMemberIdAndId(memberRequestDto.getMemberId(),character_id)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저 캐릭터가 없습니다. id"+character_id));

        //다른 main캐릭터가 있으면, 다른 캐릭터는 false로 지정
        if (Boolean.TRUE.equals(memberRequestDto.getIs_main())) {
            List<UserCharacter> mainCharacters = userCharacterRepository.findByMemberIdAndMain(userCharacter.getMember().getId(), Boolean.TRUE);
            if (!mainCharacters.isEmpty()) {
                for (UserCharacter mainCharacter : mainCharacters) {
                    if (!mainCharacter.getId().equals(userCharacter.getId())) {
                        mainCharacter.updateMain(false);
                        userCharacterRepository.save(mainCharacter);
                    }
                }
            }
        }
        userCharacter.updateMain(memberRequestDto.getIs_main());
    }

    //채팅도 사용자id넘겨줘서 구현하기


}
