package com.bonobono.backend.domain.community.article.dto.res;

import com.bonobono.backend.domain.community.article.entity.ArticleImage;

public class ArticleImageResponseDto {

    private String originalName;
    private String saveName;

    public ArticleImageResponseDto(ArticleImage entity){
        this.originalName = entity.getOriginalName();
        this.saveName = entity.getSaveName();
    }
}
