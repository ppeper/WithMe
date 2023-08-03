package com.bonobono.backend.character.dto;


import com.bonobono.backend.character.domain.OurCharacter;
import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleComment;
import com.bonobono.backend.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@NoArgsConstructor
public class UserChracterRequestDto {
    private String custom_name;
    private boolean is_main;
    private Long chracterId; //도감의 모든 캐릭터들의 id를 가져옴
    private Long memberId;// 현재 밈버 id

    @Builder
    public UserChracterRequestDto(String custom_name, boolean is_main, Long chracterId, Long memberId){
        this.chracterId=chracterId;
        this.custom_name=custom_name;
        this.is_main=is_main;
        this.memberId=memberId;
    }


}
