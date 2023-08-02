package com.bonobono.backend.domain.community.article.dto.req;

import com.bonobono.backend.domain.community.article.enumclass.ArticleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleTogetherRequestDto {

    private ArticleType type;
    private String title;
    private String content;
    private String image;
    private String urlTitle;
    private String url;

    @Builder
    public ArticleTogetherRequestDto(ArticleType type, String title, String content, String image, String urlTitle, String url){
        this.type = type;
        this.title = title;
        this.content = content;
        this.image = image;
        this.urlTitle = urlTitle;
        this.url = url;

    }
}
