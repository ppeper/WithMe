package com.bonobono.backend.character.dto;

import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.community.article.entity.ArticleComment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserChracterResponseDto {
    private String custom_name;
    private boolean is_main;
    private int experience;

}
