package com.bonobono.backend.community.report.entity;

import com.bonobono.backend.community.article.entity.ArticleComment;
import com.bonobono.backend.community.article.entity.ArticleImage;
import com.bonobono.backend.community.article.entity.ArticleLike;
import com.bonobono.backend.global.entity.BaseTimeEntity;
import com.bonobono.backend.member.entity.Member;
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
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private int views;

    private boolean adminCofirmStatus;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ArticleLike> articleLikes = new HashSet<>();

    @Builder
    public Report(String title, String content, String urlTitle, String url, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    // 글 수정
    public void updateFree(String title, String content){
        this.title = title;
        this.content = content;
    }
}
