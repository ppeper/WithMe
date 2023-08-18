package com.bonobono.backend.community.report.entity;

import com.bonobono.backend.global.entity.BaseTimeEntity;
import com.bonobono.backend.location.entity.Location;
import com.bonobono.backend.member.domain.Member;
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
    @Column(name = "report_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private int views;

    private double latitude;

    private double longitude;

    private boolean adminConfirmStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="location_id", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReportImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReportComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReportLike> reportLikes = new HashSet<>();

    @Builder
    public Report(String title, String content, double latitude, double longitude, Member member, Location location) {
        this.title = title;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
        this.member = member;
        this.location = location;
    }

    // 글 수정
    public void updateReport(String title, String content, double latitude, double longitude){
        this.title = title;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // 관리자 신고게시글 처리
    public void updateAdminConfirmStatus(){
        this.adminConfirmStatus = true;
    }
}
