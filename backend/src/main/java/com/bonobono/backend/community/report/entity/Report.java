package com.bonobono.backend.community.report.entity;

import com.bonobono.backend.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

//    private Member member;

//    private Location location;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String image; // 이미지 경로저장

    private int likes;

    private int views;

    private double latitude;

    private double longitude;

    private boolean adminConfirm; // 관리자 확인여부 true or false

    @Builder
    public Report(String title, String content, String image, double latitude, double longitude) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
