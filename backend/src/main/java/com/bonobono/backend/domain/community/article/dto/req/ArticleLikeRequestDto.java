package com.bonobono.backend.domain.community.article.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleLikeRequestDto {

    private Long articleId;
    private Long memberId;

    @Builder
    public ArticleLikeRequestDto(Long articleId, Long memberId){
        this.articleId = articleId;
        this.memberId = memberId;
    }
}
