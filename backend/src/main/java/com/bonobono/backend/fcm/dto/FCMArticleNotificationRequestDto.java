package com.bonobono.backend.fcm.dto;

import com.bonobono.backend.community.article.enumclass.ArticleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FCMArticleNotificationRequestDto {
    private Long memberId;
    private String title;
    private String body;
    private ArticleType type;
    private Long articleId;

    @Builder
    public FCMArticleNotificationRequestDto(Long memberId, String title, String body, ArticleType type, Long articleId){
        this.memberId = memberId;
        this.title = title;
        this.body = body;
        this.type = type;
        this.articleId = articleId;
    }
}
