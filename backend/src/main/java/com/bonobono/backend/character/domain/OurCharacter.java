package com.bonobono.backend.character.domain;

import javax.persistence.*;

@Entity
public class OurCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //하나의 사진만 올라감
    private String imageName; // 원본 파일명

    private String imageUrl; // 이미지 url

    //캐릭터 설명
    private String description;

    private int level;

}
