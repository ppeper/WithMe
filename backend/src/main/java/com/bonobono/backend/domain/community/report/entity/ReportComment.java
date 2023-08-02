package com.bonobono.backend.domain.community.report.entity;

import com.bonobono.backend.global.entity.BaseTimeEntity;

import javax.persistence.*;

@Entity
public class ReportComment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_comment_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    private int likes;

    @ManyToOne
    @JoinColumn(name="report_id")
    private Report report;
}
