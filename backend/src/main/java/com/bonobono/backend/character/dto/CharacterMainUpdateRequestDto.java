package com.bonobono.backend.character.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CharacterMainUpdateRequestDto {
    private Boolean is_main;

    @Builder
    public CharacterMainUpdateRequestDto(Boolean is_main) {
        this.is_main = is_main;
    }
}
