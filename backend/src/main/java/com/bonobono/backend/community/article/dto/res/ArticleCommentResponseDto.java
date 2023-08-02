package com.bonobono.backend.community.article.dto.res;

import com.bonobono.backend.community.article.entity.ArticleComment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleCommentResponseDto {
    private String content;
    private int likes;
    private String nickname;
    private ArticleComment parent;

    public ArticleCommentResponseDto(ArticleComment entity){
        this.content = entity.getContent();
        this.likes = entity.getLikes();
        this.nickname = entity.getMember().getNickname();
        this.parent = entity.getParent();
    }
}
