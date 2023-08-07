package com.bonobono.backend.character.domain;

import com.bonobono.backend.character.enumClass.CharacterLevelEnum;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
public class OurCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //캐릭터 설명
    private String description;

    @Enumerated(EnumType.STRING)
    private CharacterLevelEnum level;

    @Transient
    private static Map<CharacterLevelEnum, OurCharacter> levelToCharacterMap;

    @PostLoad
    public void initLevelMapping() {
        levelToCharacterMap = levelToCharacterMap == null ? new HashMap<>() : levelToCharacterMap;
        levelToCharacterMap.put(this.level, this);
    }

    public static OurCharacter getCharacterByLevel(CharacterLevelEnum newLevel) {
        return levelToCharacterMap.get(newLevel);
    }


}
