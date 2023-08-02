package com.bonobono.backend.community.article.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ArticleImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_image_id")
    private Long id;

    @Column(nullable = false)
    private String imageName; // 원본 파일명

    @Column(nullable = false)
    private String imageUrl; // 이미지 url

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="article_id")
    private Article article;// 연관 게시글

    @Builder
    public ArticleImage(String imageName, String imageUrl, Article article){
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.article = article;
    }

    // 이미지 수정
    public void updateImage(String imageName, String imageUrl){
        this.imageName = imageName;
        this.imageUrl = imageUrl;
    }

}
