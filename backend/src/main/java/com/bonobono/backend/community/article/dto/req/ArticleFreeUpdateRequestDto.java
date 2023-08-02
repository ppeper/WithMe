package com.bonobono.backend.community.article.dto.req;

import com.bonobono.backend.community.article.entity.Article;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleFreeUpdateRequestDto {

    private String title;
    private String content;

    @Builder
    public ArticleFreeUpdateRequestDto(String title, String content, String image){
        this.title = title;
        this.content = content;
    }
    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
