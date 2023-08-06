package com.bonobono.backend.community.article.entity;

import com.bonobono.backend.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ArticleCommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_comment_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="article_comment_id")
    private ArticleComment articleComment;


    @Builder
    public ArticleCommentLike(ArticleComment articleComment, Article article, Member member){
        this.articleComment = articleComment;
        this.article = article;
        this.member = member;

    }
}
