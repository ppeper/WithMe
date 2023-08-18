package com.bonobono.backend.community.article.dto.req;

import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.enumclass.ArticleType;
import com.bonobono.backend.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleSaveRequestDto {

    private String title;
    private String content;
    private String urlTitle;
    private String url;


    @Builder
    public ArticleSaveRequestDto(String title,
                                 String content,
                                 String urlTitle,
                                 String url){
        this.title = title;
        this.content = content;
        this.urlTitle = urlTitle;
        this.url = url;
    }

    public Article toEntity(ArticleType type, Member member){
        return Article.builder()
                .type(type)
                .title(title)
                .content(content)
                .urlTitle(urlTitle)
                .url(url)
                .member(member)
                .build();

    }

}
