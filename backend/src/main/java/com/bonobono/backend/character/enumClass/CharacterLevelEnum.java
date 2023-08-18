package com.bonobono.backend.character.enumClass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CharacterLevelEnum {
    LEVEL_1(1),
    LEVEL_2(2),
    LEVEL_3(3);

    private final int level;
}

