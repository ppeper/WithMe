package com.bonobono.backend.character.dto.basket;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CharacterMainUpdateRequestDto {
    private Boolean is_main;
    private Long memberId;

    @Builder
    public CharacterMainUpdateRequestDto(Boolean is_main, Long memberId) {
        this.is_main = is_main;
        this.memberId=memberId;
    }
}
