package com.bonobono.backend.character.domain;


import com.bonobono.backend.community.article.enumclass.ArticleType;
import com.bonobono.backend.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
public class UserCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="character_id")
    private OurCharacter ourCharacter;

    private String cutstom_name;

    //경험치
    private int experience;

    //잡은 날짜는 update할 수 없다
    @Column(name = "catch_date", updatable = false)
    private LocalDate catch_date;

    private boolean is_main;

}
