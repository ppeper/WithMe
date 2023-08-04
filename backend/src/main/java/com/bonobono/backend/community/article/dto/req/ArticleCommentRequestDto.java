package com.bonobono.backend.community.article.dto.req;

import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleComment;
import com.bonobono.backend.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleCommentRequestDto {

    private String content;
    private Long memberId; // User 나중에 변경
    private Long parentCommentId;

    @Builder
    public ArticleCommentRequestDto(String content, Long memberId, Long parentCommentId){
        this.content = content;
        this.memberId = memberId;
        this.parentCommentId = parentCommentId;
    }
    public ArticleComment toEntity(Article article, Member member, ArticleComment parentComment) {
        return ArticleComment.builder()
                .content(content)
                .article(article)
                .member(member)
                .parentComment(parentComment)
                .build();
    }
}

