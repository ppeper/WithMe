package com.bonobono.backend.domain.community.article.dto.res;

import com.bonobono.backend.domain.community.article.entity.Article;
import com.bonobono.backend.domain.community.article.entity.ArticleComment;
import com.bonobono.backend.domain.community.article.enumclass.ArticleType;

import java.util.List;

public class ArticleFreeDetailResponseDto {

    private Long id;
    private ArticleType type;
    private String title;
    private String content;
    private String image;
    private int likes;
    private int views;
    private List<ArticleComment> comments;

    public ArticleFreeDetailResponseDto(Article entity){
        this.id = entity.getId();
        this.type = entity.getType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.image = entity.getImage();
        this.likes = entity.getLikes();
        this.views = entity.getViews();
        this.comments = entity.getComments();
    }

}
