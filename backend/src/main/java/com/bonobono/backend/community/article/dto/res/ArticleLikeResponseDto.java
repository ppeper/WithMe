package com.bonobono.backend.community.article.dto.res;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleLikeResponseDto {

    int likes;
    boolean isLiked;

    public ArticleLikeResponseDto(int likes, boolean isLiked) {
        this.likes = likes;
        this.isLiked = isLiked;
    }
}