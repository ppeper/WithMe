package com.bonobono.backend.character.dto;

import com.bonobono.backend.character.domain.OurCharacter;
import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.character.enumClass.CharacterLevelEnum;
import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.community.article.entity.ArticleComment;
import com.bonobono.backend.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class UserChracterResponseDto {
    private String custom_name;
    private Boolean is_main;
    private Integer experience;
    private LocalDateTime createdDate;
    private CharacterLevelEnum level;
    private String description;
    private Long memberId;


    public UserChracterResponseDto(UserCharacter userCharacter) {
        this.custom_name = userCharacter.getCustom_name();
        this.is_main=userCharacter.getMain();
        this.experience=userCharacter.getExperience();
        this.createdDate=LocalDateTime.now();
        //ourcharacter에서 가져온 정보
        this.level = userCharacter.getOurCharacter().getLevel();
        this.description = userCharacter.getOurCharacter().getDescription();
        this.memberId=userCharacter.getMember().getId();
            }
}
