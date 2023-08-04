package com.bonobono.backend.community.article.dto.req;

import com.bonobono.backend.community.article.entity.Article;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleUpdateRequestDto {

    private String title;
    private String content;
    private String urlTitle;
    private String url;

    @Builder
    public ArticleUpdateRequestDto(String title,
                                   String content,
                                   String urlTitle,
                                   String url){
        this.title = title;
        this.content = content;
        this.urlTitle = urlTitle;
        this.url = url;
    }
    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .urlTitle(urlTitle)
                .url(url)
                .build();
    }
}
