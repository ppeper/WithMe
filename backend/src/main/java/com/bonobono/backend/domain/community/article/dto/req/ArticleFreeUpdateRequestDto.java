package com.bonobono.backend.domain.community.article.dto.req;

import com.bonobono.backend.domain.community.article.entity.Article;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleFreeUpdateRequestDto {

    private String title;
    private String content;
    private String image;

    @Builder
    public ArticleFreeUpdateRequestDto(String title, String content, String image){
        this.title = title;
        this.content = content;
        this.image = image;
    }
    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .image(image)
                .build();
    }
}
