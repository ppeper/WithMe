package com.bonobono.backend.community.report.entity;

import com.bonobono.backend.global.entity.BaseTimeEntity;
import com.bonobono.backend.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@NoArgsConstructor
@Entity
public class ReportComment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_comment_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name="report_id", nullable = false)
    private Report report;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_comment_id")
    private ReportComment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<ReportComment> childComments = new ArrayList<>();

    @Builder
    public ReportComment(String content, Report report, Member member, ReportComment parentComment){
        this.content = content;
        this.report = report;
        this.member = member;
        this.parentComment = parentComment;
    }

    // 댓글 수정
    public void updateComment(String content){
        this.content = content;
    }

    // 대댓글 입력
    public void addChildComment(ReportComment childComment){
        this.childComments.add(childComment);
    }
}
