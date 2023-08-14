package com.bonobono.backend.character.dto.basket;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CharacterMainUpdateRequestDto {
    private boolean main;

    @Builder
    public CharacterMainUpdateRequestDto(boolean main) {
        this.main = main;
    }
}
