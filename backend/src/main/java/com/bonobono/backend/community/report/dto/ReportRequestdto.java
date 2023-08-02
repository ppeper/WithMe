package com.bonobono.backend.community.report.dto;

import com.bonobono.backend.community.report.entity.Report;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportRequestdto {

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

    public ReportRequestdto(Report entity){
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

    public Report toEntity(){
        return Report.builder()
                .content(content)
                .image(image)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }

}
