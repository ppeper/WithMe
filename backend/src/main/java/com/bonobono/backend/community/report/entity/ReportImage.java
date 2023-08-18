package com.bonobono.backend.community.report.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ReportImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_image_id")
    private Long id;

    @Column(nullable = false)
    private String imageName; // 원본 파일명

    @Column(nullable = false)
    private String imageUrl; // 이미지 url

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="report_id")
    private Report report;// 연관 게시글

    @Builder
    public ReportImage(String imageName, String imageUrl, Report report){
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.report = report;
    }

    // 이미지 수정
    public void updateImage(String imageName, String imageUrl){
        this.imageName = imageName;
        this.imageUrl = imageUrl;
    }
}
