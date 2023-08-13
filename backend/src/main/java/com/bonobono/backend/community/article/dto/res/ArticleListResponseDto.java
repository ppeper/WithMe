package com.bonobono.backend.community.article.dto.res;


import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.enumclass.ArticleType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ArticleListResponseDto {

    private Long articleId;
    private ArticleType type;
    private String title;
    private String content;
    private int views;
    private boolean recruitStatus;
    private ArticleImageResponseDto images;
    private int imageSize;
    private int likes;
    private int comments;
    private String nickname;
    private String profileImg;
    private LocalDateTime createdDate;

    public ArticleListResponseDto(Article entity) {
        this.articleId = entity.getId();
        this.type = entity.getType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.views = entity.getViews();
        this.recruitStatus = entity.isRecruitStatus();
        this.images = entity.getImages().stream()
                .map(ArticleImageResponseDto::new)
                .findFirst().orElse(null);
        this.imageSize = entity.getImages().size();
        this.likes = entity.getArticleLikes().size();
        this.comments = entity.getComments().size();
        this.nickname = entity.getMember().getNickname();
        this.profileImg = entity.getMember().getProfileImg().getImageUrl();
        this.createdDate = entity.getCreatedDate();
    }
}

