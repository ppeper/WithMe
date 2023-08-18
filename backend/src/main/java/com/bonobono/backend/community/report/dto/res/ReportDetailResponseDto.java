package com.bonobono.backend.community.report.dto.res;

import com.bonobono.backend.community.report.entity.Report;
import com.bonobono.backend.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ReportDetailResponseDto {

    private String title;
    private String content;
    private Long memberId;
    private String nickname;
    private String profileImg;
    private int views;
    private String locationName;
    private double latitude;
    private double longitude;
    private boolean adminConfirmStatus;
    private List<ReportImageResponseDto> images;
    private int likes;
    private boolean isLiked;
    private List<ReportCommentResponseDto> comments;
    private int commentCnt;
    private LocalDateTime createdDate;



    public ReportDetailResponseDto(Report entity, Member member, List<ReportCommentResponseDto> comments){
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.memberId = entity.getMember().getId();
        this.nickname = entity.getMember().getNickname();
        this.profileImg = entity.getMember().getProfileImg().getImageUrl();
        this.views = entity.getViews();
        this.locationName = entity.getLocation().getName();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.adminConfirmStatus = entity.isAdminConfirmStatus();
        this.images = entity.getImages().stream()
                .map(ReportImageResponseDto::new)
                .collect(Collectors.toList());
        this.likes = entity.getReportLikes().size();
        this.isLiked =  entity.getReportLikes().stream().anyMatch(like -> like.getMember().getId().equals(member.getId()));
        this.comments = comments;
        this.commentCnt = entity.getComments().size();
        this.createdDate = entity.getCreatedDate();

    }

}
