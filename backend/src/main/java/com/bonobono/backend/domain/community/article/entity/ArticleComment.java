package com.bonobono.backend.domain.community.article.entity;

import com.bonobono.backend.global.entity.BaseTimeEntity;
import com.bonobono.backend.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="article_id")
    @OnDelete(action = OnDeleteAction.CASCADE) // 연관된 article 삭제되면 comment도 삭제
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    @OnDelete(action = OnDeleteAction.CASCADE) // 연관된 member가 삭제되면 comment도 삭제
    private Member member;

    @Builder
    public ArticleComment(String content, Article article, Member member){
        this.content = content;
        this.article = article;
        this.member = member;
    }
}
