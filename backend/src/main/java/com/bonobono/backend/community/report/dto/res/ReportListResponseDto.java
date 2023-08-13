package com.bonobono.backend.community.report.dto.res;


import com.bonobono.backend.community.report.entity.Report;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReportListResponseDto {

    private Long reportId;
    private String title;
    private String content;
    private String nickname;
    private String profileImg;
    private int views;
    private boolean adminConfirmStatus;
    private ReportImageResponseDto images;
    private int imageSize;
    private int likes;
    private int comments;

    private LocalDateTime createdDate;

    public ReportListResponseDto(Report entity) {
        this.reportId = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.nickname = entity.getMember().getNickname();
        this.profileImg = entity.getMember().getProfileImg().getImageUrl();
        this.views = entity.getViews();
        this.adminConfirmStatus = entity.isAdminConfirmStatus();
        this.images = entity.getImages().stream()
                .map(ReportImageResponseDto::new)
                .findFirst().orElse(null);
        this.imageSize = entity.getImages().size();
        this.likes = entity.getReportLikes().size();
        this.comments = entity.getComments().size();
        this.createdDate = entity.getCreatedDate();
    }
}

