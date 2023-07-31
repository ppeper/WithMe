package com.bonobono.backend.domain.community.article.dto.req;

import com.bonobono.backend.domain.community.article.entity.Article;
import com.bonobono.backend.domain.community.article.entity.ArticleImage;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleImageRequestDto {

    private String originalName;
    private String saveName;

    public ArticleImage toEntity(Article article){
        return ArticleImage.builder()
                .originalName(originalName)
                .saveName(saveName)
                .article(article)
                .build();
    }

}
