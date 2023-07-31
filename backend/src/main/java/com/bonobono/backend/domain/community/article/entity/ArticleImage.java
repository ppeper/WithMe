package com.bonobono.backend.domain.community.article.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class ArticleImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_image_id")
    private Long id;

    @Column(nullable = false)
    private String originalName; // 원본 파일명

    @Column(nullable = false)
    private String saveName; // 실제 저장 파일명

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="article_id")
    private Article article;// 연관 게시글

    @Builder
    public ArticleImage(String originalName, String saveName, Article article){
        this.originalName = originalName;
        this.saveName = saveName;
        this.article = article;
    }

    // 첫번째 이미지만 받기
    public ArticleImage getFirstImage(Article entity){
        List<ArticleImage> images = entity.getImages();
        if(images.isEmpty()){
            return null;
        } else{
            return images.get(0);
        }
    }

    // 이미지 수정
    public void updateImage(String originalName, String saveName){
        this.originalName = originalName;
        this.saveName = saveName;
    }

}
