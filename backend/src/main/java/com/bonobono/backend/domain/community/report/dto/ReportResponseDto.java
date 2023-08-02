package com.bonobono.backend.domain.community.report.dto;

import com.bonobono.backend.domain.community.report.entity.Report;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportResponseDto {

    private Long id;
//    private Long memberId;

//    private Long locationId;
    private String title;
    private String content;
    private String image;
    private int likes;
    private int views;
    private double latitude;
    private double longitude;
    private boolean adminConfirm;

    public ReportResponseDto(Report entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.image = entity.getImage();
        this.likes = entity.getLikes();
        this.views = entity.getViews();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.adminConfirm = entity.isAdminConfirm();
    }

}
