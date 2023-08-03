package com.bonobono.backend.community.article.dto.res;


import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.enumclass.ArticleType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleTogetherResponseDto {

    private Long id;
    private ArticleType type;
    private String title;
    private String content;
    private int views;
    private int likes;
    private boolean recruitStatus;
    private String urlTitle;
    private String url;

    public ArticleTogetherResponseDto(Article entity) {
        this.id = entity.getId();
        this.type = entity.getType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.views = entity.getViews();
        this.likes = entity.getArticleLikes().size();
        this.recruitStatus = entity.isRecruitStatus();
        this.urlTitle = entity.getUrlTitle();
        this.url = entity.getUrl();
    }
}

