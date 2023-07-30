package com.bonobono.backend.domain.community.article.dto.res;


import com.bonobono.backend.domain.community.article.entity.Article;
import com.bonobono.backend.domain.community.article.enumclass.ArticleType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleFreeListResponseDto {

    private ArticleType type;
    private String title;
    private String content;
    private String image;
    private int likes;
    private int views;
    private Long memberId;

    public ArticleFreeListResponseDto(Article entity) {
        this.type = entity.getType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.image = entity.getImage();
        this.likes = entity.getLikes();
        this.views = entity.getViews();
        this.memberId = entity.getMember().getId();
    }
}

