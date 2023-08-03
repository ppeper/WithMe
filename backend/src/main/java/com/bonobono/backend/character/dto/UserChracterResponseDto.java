package com.bonobono.backend.character.dto;

import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.community.article.entity.ArticleComment;

public class UserChracterResponseDto {
    private String custom_name;
    private boolean is_main;
    private int experience;

    public UserChracterResponseDto(UserCharacter userCharacter){
        this.custom_name= userCharacter.getCustom_name();
        this.is_main=userCharacter.is_main();
        this.experience=userCharacter.getExperience();
    }
}
