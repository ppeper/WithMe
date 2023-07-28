package com.bonobono.backend.domain.community.article.entity;

import com.bonobono.backend.global.entity.BaseTimeEntity;
import com.bonobono.backend.domain.member.entity.Member;
import com.bonobono.backend.domain.community.article.enumclass.ArticleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    private String image;

    private int likes;

    private int views;

    private boolean recruitStatus;

    private String urlTitle;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    @OnDelete(action = OnDeleteAction.CASCADE) // 연관된 member가 삭제되면 article도 삭제
    private Member member;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<ArticleComment> comments = new ArrayList<>();

    @Builder
    public Article(ArticleType type, String title, String content, String image, String urlTitle, String url, Member member) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.image = image;
        this.urlTitle = urlTitle;
        this.url = url;
        this.member = member;
    }

    // 조회 시 조회수 증가
    public void increaseViews(int views){
        this.views = views + 1;
    }

    // 글 수정
    public void updateFree(String title, String content, String image){
        this.title = title;
        this.content = content;
        this.image = image;
    }
}
