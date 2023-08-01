package com.bonobono.backend.domain.community.article.dto.req;

import com.bonobono.backend.domain.community.article.entity.Article;
import com.bonobono.backend.domain.community.article.entity.ArticleComment;
import com.bonobono.backend.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleCommentRequestDto {

    private String content;
    private Long memberId; // User 나중에 변경
    private Long parentId;

    @Builder
    public ArticleCommentRequestDto(String content, Long memberId, Long parentId){
        this.content = content;
        this.memberId = memberId;
        this.parentId = parentId;
    }
    public ArticleComment toEntity(Article article, Member member, ArticleComment parent) {
        return ArticleComment.builder()
                .content(content)
                .article(article)
                .member(member)
                .parent(parent)
                .build();
    }
}

