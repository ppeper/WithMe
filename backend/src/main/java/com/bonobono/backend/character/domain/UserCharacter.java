package com.bonobono.backend.character.domain;


import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleComment;
import com.bonobono.backend.community.article.enumclass.ArticleType;
import com.bonobono.backend.global.entity.BaseTimeEntity;
import com.bonobono.backend.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class UserCharacter extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member; //유저 정보(한 유저가 여러개의 캐릭터를 가짐)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="character_id")
    private OurCharacter ourCharacter; //캐릭터id(정보를가지고 있음)

    private String custom_name;

    //경험치
    private int experience;

//    //잡은 날짜는 update할 수 없다(JPA의 createdate를 catchdate로 사용)
//    @Column(name = "catch_date", updatable = false)
//    private LocalDate catch_date;

    private boolean is_main;

    @Builder
    public UserCharacter(OurCharacter ourCharacter, Member member, boolean is_main, String custom_name){
        this.is_main = is_main;
        this.ourCharacter = ourCharacter;
        this.member = member;
        this.custom_name = custom_name;
    }

    //경험치 수정
    public void updateExperience(int experience){
        this.experience = experience;
    }
}
