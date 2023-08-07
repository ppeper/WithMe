package com.bonobono.backend.character.domain;

import com.bonobono.backend.character.enumClass.CharacterLevelEnum;
import lombok.Getter;

import javax.persistence.*;

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


}
