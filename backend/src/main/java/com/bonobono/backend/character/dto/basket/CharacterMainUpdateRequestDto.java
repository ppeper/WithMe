package com.bonobono.backend.character.dto.basket;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CharacterMainUpdateRequestDto {
    private boolean main;
    private Long memberId;

    @Builder
    public CharacterMainUpdateRequestDto(boolean main, Long memberId) {
        this.main = main;
        this.memberId=memberId;
    }
}
