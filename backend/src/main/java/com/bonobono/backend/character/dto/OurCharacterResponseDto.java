package com.bonobono.backend.character.dto;

import com.bonobono.backend.character.domain.OurCharacter;
import com.bonobono.backend.character.domain.UserCharacter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OurCharacterResponseDto {
    private int level;
    private String ImageUrl;
    private String ImageName;
    private String description;
    private String name;


    public OurCharacterResponseDto(OurCharacter ourCharacter) {
        //ourcharacter에서 가져온 정보
        this.level = ourCharacter.getLevel();
        this.ImageUrl = ourCharacter.getImageUrl();
        this.ImageName = ourCharacter.getImageName();
        this.description = ourCharacter.getDescription();
        this.name = ourCharacter.getName();
    }

}
