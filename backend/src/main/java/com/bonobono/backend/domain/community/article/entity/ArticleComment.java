package com.bonobono.backend.domain.community.article.entity;

import com.bonobono.backend.domain.member.entity.Member;
import com.bonobono.backend.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ArticleComment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_comment_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    private int likes;

    @ManyToOne
    @JoinColumn(name="article_id", nullable = false)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_comment_id", nullable = true)
    private ArticleComment parentComment;

    @Builder
    public ArticleComment(String content, Article article, Member member, ArticleComment parentComment){
        this.content = content;
        this.article = article;
        this.member = member;
        this.parentComment = parentComment;
    }

    public void updateComment(String content){
        this.content = content;
    }
}
