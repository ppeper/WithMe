package com.bonobono.backend.character.dto.catchCharacter;

import com.bonobono.backend.character.domain.OurCharacter;
import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCharacterWithSeaRequestDto {

    //해변이름과 ourcharacter id를 넘겨주면 됨
    private String location_name;
    private Long ourCharacterId;
    //custom_name넣어주기
    private String custom_name;

    @Builder
    public UserCharacterWithSeaRequestDto(String location_name, Long ourCharacterId, String custom_name) {
        this.custom_name=custom_name;
        this.ourCharacterId=ourCharacterId;
        this.location_name=location_name;
    }

    public UserCharacter toEntity(String custom_name, OurCharacter ourCharacter, String location_name, Member member) {
        return UserCharacter.builder()
                .ourCharacter(ourCharacter)
                .member(member)
                .customName(custom_name)
                .locationName(location_name)
                .build();
    }
}
