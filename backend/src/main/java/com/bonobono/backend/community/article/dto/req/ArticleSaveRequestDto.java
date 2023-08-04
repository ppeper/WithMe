package com.bonobono.backend.community.article.dto.req;

import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.enumclass.ArticleType;
import com.bonobono.backend.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleSaveRequestDto {

    private ArticleType type;
    private String title;
    private String content;
    private String urlTitle;
    private String url;
    private Long memberId; // 후에 user 현재 정보로 바꾸기


    @Builder
    public ArticleSaveRequestDto(ArticleType type,
                                 String title,
                                 String content,
                                 String urlTitle,
                                 String url,
                                 Long memberId){
        this.type = type;
        this.title = title;
        this.content = content;
        this.urlTitle = urlTitle;
        this.url = url;
        this.memberId = memberId;
    }

    public Article toEntity(Member member){
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
