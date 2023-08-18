package com.bonobono.backend.character.dto;

import com.bonobono.backend.character.domain.OurCharacter;
import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.character.enumClass.CharacterLevelEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OurCharacterResponseDto {
    private Long id;
    private CharacterLevelEnum level;
    private String description;
    private String name;


    public OurCharacterResponseDto(OurCharacter ourCharacter) {
        //ourcharacter에서 가져온 정보
        this.id=ourCharacter.getId();
        this.level = ourCharacter.getLevel();
        this.description = ourCharacter.getDescription();
        this.name = ourCharacter.getName();
    }

}
