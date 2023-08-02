package com.bonobono.backend.domain.community.article.dto.res;

import com.bonobono.backend.domain.community.article.entity.ArticleImage;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleImageResponseDto {

    private String imageName;
    private String imageUrl;

    public ArticleImageResponseDto(ArticleImage entity){
        this.imageName = entity.getImageName();
        this.imageUrl = entity.getImageUrl();
    }
}
