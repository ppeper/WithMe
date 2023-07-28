package com.bonobono.backend.domain.community.article.dto.req;

import com.bonobono.backend.domain.community.article.entity.Article;
import com.bonobono.backend.domain.community.article.enumclass.ArticleType;
import com.bonobono.backend.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleFreeSaveRequestDto {

    private ArticleType type;
    private String title;
    private String content;
    private String image;
    private Long memberId;


    @Builder
    public ArticleFreeSaveRequestDto(ArticleType type, String title, String content, String image, Long memberId){
        this.type = type;
        this.title = title;
        this.content = content;
        this.image = image;
        this.memberId = memberId;
    }

    public Article toEntity(Member member){
        return Article.builder()
                .type(type)
                .title(title)
                .content(content)
                .image(image)
                .member(member)
                .build();
    }
}
