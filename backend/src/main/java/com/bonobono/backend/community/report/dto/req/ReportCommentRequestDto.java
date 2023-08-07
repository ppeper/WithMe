package com.bonobono.backend.community.report.dto.req;

import com.bonobono.backend.community.report.entity.Report;
import com.bonobono.backend.community.report.entity.ReportComment;
import com.bonobono.backend.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportCommentRequestDto {

    private String content;
    private Long parentCommentId;

    public ReportCommentRequestDto(String content, Long parentCommentId){
        this.content = content;
        this.parentCommentId = parentCommentId;
    }

    public ReportComment toEntity(Report report, Member member, ReportComment parentComment) {
        return ReportComment.builder()
                .content(content)
                .report(report)
                .member(member)
                .parentComment(parentComment)
                .build();
    }
}
