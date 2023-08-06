package com.bonobono.backend.community.article.entity;

import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name="article_id", nullable = false)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_comment_id")
    private ArticleComment parentComment;

    @OneToMany(mappedBy = "parentComment", orphanRemoval = true)
    private List<ArticleComment> childComments = new ArrayList<>();

    @OneToMany(mappedBy = "articleComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ArticleCommentLike> articleCommentLikes = new HashSet<>();


    @Builder
    public ArticleComment(String content, Article article, Member member, ArticleComment parentComment){
        this.content = content;
        this.article = article;
        this.member = member;
        this.parentComment = parentComment;
    }

    // 댓글 수정
    public void updateComment(String content){
        this.content = content;
    }

    // 대댓글 입력
    public void addChildComment(ArticleComment childComment){
        this.childComments.add(childComment);
    }
}
