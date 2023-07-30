package com.bonobono.backend.domain.community.article.dto.res;

import com.bonobono.backend.domain.community.article.entity.ArticleComment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleCommentResponseDto {
    private String content;
    private int likes;
    private Long memberId;

    public ArticleCommentResponseDto(ArticleComment entity){
        this.content = entity.getContent();
        this.likes = entity.getLikes();
        this.memberId = entity.getMember().getId();
    }
}
