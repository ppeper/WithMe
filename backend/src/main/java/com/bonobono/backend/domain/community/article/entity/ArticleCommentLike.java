package com.bonobono.backend.domain.community.article.entity;

import com.bonobono.backend.domain.member.entity.Member;
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

    @ManyToOne
    @JoinColumn(name="article_comment_id")
    private ArticleComment articleComment;

    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    public ArticleCommentLike(ArticleComment articleComment, Article article, Member member){
        this.articleComment = articleComment;
        this.article = article;
        this.member = member;

    }
}
