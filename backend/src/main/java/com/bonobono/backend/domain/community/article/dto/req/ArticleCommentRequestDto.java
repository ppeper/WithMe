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
    private Long articleId;
    private Long memberId;

    @Builder
    public ArticleCommentRequestDto(String content, Long articleId, Long memberId){
        this.content = content;
        this.articleId = articleId;
        this.memberId = memberId;
    }
    public ArticleComment toEntity(Article article, Member member) {
        return ArticleComment.builder()
                .content(content)
                .article(article)
                .member(member)
                .build();
    }
}

