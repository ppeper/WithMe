package com.bonobono.backend.community.article.dto.res;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleCommentLikeResponseDto {

    int likes;
    boolean isLiked;

    public ArticleCommentLikeResponseDto(int likes, boolean isLiked) {
        this.likes = likes;
        this.isLiked = isLiked;
    }
}
