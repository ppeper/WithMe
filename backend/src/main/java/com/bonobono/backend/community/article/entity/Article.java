package com.bonobono.backend.community.article.entity;

import com.bonobono.backend.community.article.enumclass.ArticleType;
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
public class Article extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ArticleType type; // FREE or TOGETHER

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private int views;

    private boolean recruitStatus;

    private String urlTitle;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<ArticleImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<ArticleComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<ArticleLike> articleLikes = new HashSet<>();

    @Builder
    public Article(ArticleType type, String title, String content, String urlTitle, String url, Member member) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.urlTitle = urlTitle;
        this.url = url;
        this.member = member;
    }

    // 글 수정
    public void updateArticle(String title, String content, String urlTitle, String url){
        this.title = title;
        this.content = content;
        this.urlTitle = urlTitle;
        this.url = url;
    }

    // 모집 완료
    public void updateRecruitStatus(){
        this.recruitStatus = true;
    }
}
