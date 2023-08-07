package com.bonobono.backend.character.dto;

import com.bonobono.backend.community.article.entity.Article;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CharacterNameUpdateRequestDto {
    private String custom_name;

    @Builder
    public CharacterNameUpdateRequestDto(String custom_name) {
        this.custom_name = custom_name;
    }


}
