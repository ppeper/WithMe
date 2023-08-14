package com.bonobono.backend.character.service;

import com.bonobono.backend.character.domain.OurCharacter;
import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.character.dto.OurCharacterResponseDto;
import com.bonobono.backend.character.dto.basket.CharacterMainUpdateRequestDto;
import com.bonobono.backend.character.dto.UserChracterResponseDto;
import com.bonobono.backend.character.dto.catchCharacter.UserCharacterWithSeaRequestDto;
import com.bonobono.backend.character.repository.OurCharacterRepository;
import com.bonobono.backend.character.repository.UserCharacterRepository;
import com.bonobono.backend.global.util.SecurityUtil;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class UserCharacterService {

    private final UserCharacterRepository userCharacterRepository;
    private final OurCharacterRepository ourCharacterRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<UserChracterResponseDto> UserfindByList() {
        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        return userCharacterRepository.findByMemberId(member.getId())
                    .stream()
                    .map(UserChracterResponseDto::new)
                    .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserChracterResponseDto findById(Long character_id) {
        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        UserCharacter userCharacter = userCharacterRepository.findByMemberIdAndId(member.getId(),character_id)
                .orElseThrow(() -> new IllegalArgumentException("유저에게 캐릭터가 없습니다 id ="+character_id));
        return new UserChracterResponseDto(userCharacter);
    }

//    @Transactional
//    public void updateName(Long character_id, CharacterNameUpdateRequestDto memberRequestDto) {
//        UserCharacter userCharacter = userCharacterRepository.findByMemberIdAndId(memberRequestDto.getMemberId(),character_id)
//                .orElseThrow(()-> new IllegalArgumentException("해당 유저 캐릭터가 없습니다 id:"+character_id));
//        userCharacter.updateName(memberRequestDto.getCustom_name());
//    }

    @Transactional
    public void updateMain(Long character_id, CharacterMainUpdateRequestDto memberRequestDto) {
        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
        UserCharacter userCharacter = userCharacterRepository.findByMemberIdAndId(member.getId(),character_id)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저 캐릭터가 없습니다. id"+character_id));

        //다른 main캐릭터가 있으면, 다른 캐릭터는 false로 지정
        if (Boolean.TRUE.equals(memberRequestDto.isMain())) {
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
        userCharacter.updateMain(memberRequestDto.isMain());
    }

    // user가 가지고 있지 않은 our캐릭터
    @Transactional(readOnly = true)
    public List<OurCharacterResponseDto> OurfindByList() {
        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        return ourCharacterRepository.findNotLinkedOurCharactersByMemberAndLevel(member.getId())
                .stream()
                .map(OurCharacterResponseDto::new)
                .collect(Collectors.toList());
    }

    //LocationOurCharacter의 id와 custom한 이름을 주면, userchar을 save()
    @Transactional
    public void save(UserCharacterWithSeaRequestDto requestDto) {
        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        OurCharacter ourCharacter = ourCharacterRepository.findById(requestDto.getOurCharacterId())
                .orElseThrow(() -> new IllegalArgumentException("해당 캐릭터가 없습니다. id =" + requestDto.getOurCharacterId()));

        List<UserCharacter> existingUserCharacter = userCharacterRepository.findByMemberAndOurCharacterAndLocationName(member, ourCharacter, requestDto.getLocation_name());

        //만약 회원과 해변, 멤버 모두 같으면, 저장을 하는게 아니라, 모두 같은 객체가 있으면, userchar의 count를 증가시켜야 함
        if (existingUserCharacter != null && !existingUserCharacter.isEmpty()) {
            UserCharacter userCharacter = existingUserCharacter.get(0);
            userCharacter.increaseCatchCount();
            userCharacter.updateCustomName(requestDto.getCustom_name());
        }
        else {
            UserCharacter userCharacter = requestDto.toEntity(requestDto.getCustom_name(), ourCharacter, requestDto.getLocation_name(), member);
            userCharacterRepository.save(userCharacter);
        }
    }
}
