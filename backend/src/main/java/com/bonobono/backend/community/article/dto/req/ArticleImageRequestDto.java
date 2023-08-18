package com.bonobono.backend.community.article.dto.req;

import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleImageRequestDto {

    private String imageName;
    private String imageUrl;

    @Builder
    public ArticleImageRequestDto(String imageName, String imageUrl){
        this.imageName = imageName;
        this.imageUrl = imageUrl;
    }

    public ArticleImage toEntity(Article article){
        return ArticleImage.builder()
                .imageName(imageName)
                .imageUrl(imageUrl)
                .article(article)
                .build();
    }

}
