package com.bonobono.backend.domain.community.article.dto.req;

import com.bonobono.backend.domain.community.article.entity.Article;
import com.bonobono.backend.domain.community.article.entity.ArticleImage;
import com.bonobono.backend.domain.community.article.enumclass.ArticleType;
import com.bonobono.backend.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ArticleFreeSaveRequestDto {

    private ArticleType type;
    private String title;
    private String content;
    private List<ArticleImageRequestDto> images;
    private Long memberId; // 후에 user 현재 정보로 바꾸기


    @Builder
    public ArticleFreeSaveRequestDto(ArticleType type, String title, String content,  List<ArticleImageRequestDto> images, Long memberId){
        this.type = type;
        this.title = title;
        this.content = content;
        this.images = images;
        this.memberId = memberId;
    }

    public Article toEntity(Member member){
        Article article = Article.builder()
                .type(type)
                .title(title)
                .content(content)
                .member(member)
                .build();

        for (ArticleImageRequestDto image : images){
            ArticleImage articleImage = image.toEntity(article);
            article.getImages().add(articleImage);
        }
        return article;
    }

}
