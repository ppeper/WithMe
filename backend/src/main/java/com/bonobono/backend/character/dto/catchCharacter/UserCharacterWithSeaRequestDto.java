package com.bonobono.backend.character.dto.catchCharacter;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCharacterWithSeaRequestDto {

    //LocationOurCharacter id

    //custom_name넣어주기
    private String custom_name;

    @Builder
    public UserCharacterWithSeaRequestDto(String custom_name) {
        this.custom_name=custom_name;
    }

}
