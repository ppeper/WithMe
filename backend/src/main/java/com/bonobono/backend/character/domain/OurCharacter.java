package com.bonobono.backend.character.domain;

import com.bonobono.backend.character.enumClass.CharacterLevelEnum;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class OurCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "char_ord_id", nullable = false)
    private Long charOrdId;

    private String name;

    //캐릭터 설명
    private String description;

    @Enumerated(EnumType.STRING)
    private CharacterLevelEnum level;

//    @OneToMany(mappedBy = "ourCharacter", fetch = FetchType.LAZY)
//    private List<LocationOurCharacter> locationOurCharacters = new ArrayList<>();

}