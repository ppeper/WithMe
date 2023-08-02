package com.bonobono.backend.domain.community.article.dto.res;


import com.bonobono.backend.domain.community.article.entity.Article;
import com.bonobono.backend.domain.community.article.enumclass.ArticleType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleTogetherResponseDto {

    private Long id;
    private ArticleType type;
    private String title;
    private String content;
    private int likes;
    private int views;
    private boolean recruitStatus;
    private String urlTitle;
    private String url;

    public ArticleTogetherResponseDto(Article entity) {
        this.id = entity.getId();
        this.type = entity.getType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.likes = entity.getLikes();
        this.views = entity.getViews();
        this.recruitStatus = entity.isRecruitStatus();
        this.urlTitle = entity.getUrlTitle();
        this.url = entity.getUrl();
    }
}

