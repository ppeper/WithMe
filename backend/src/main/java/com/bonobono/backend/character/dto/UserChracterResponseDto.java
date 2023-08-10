package com.bonobono.backend.character.dto;

import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.character.enumClass.CharacterLevelEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserChracterResponseDto {
    private Long id;
    private Long char_ord_id;
    private String custom_name;
    private Boolean is_main;
    private Integer experience;
    private LocalDateTime createdDate;
    private CharacterLevelEnum level;
    private String description;
    private Long memberId;


    public UserChracterResponseDto(UserCharacter userCharacter) {
        this.id = userCharacter.getId();
        this.custom_name = userCharacter.getCustom_name();
        this.is_main=userCharacter.getMain();
        this.experience=userCharacter.getExperience();
        this.createdDate=LocalDateTime.now();

        //ourcharacter에서 가져온 정보
        this.char_ord_id = userCharacter.getOurCharacter().getCharOrdId();
        this.level = userCharacter.getOurCharacter().getLevel();
        this.description = userCharacter.getOurCharacter().getDescription();
        this.memberId=userCharacter.getMember().getId();
            }
}
