package com.bonobono.backend.community.report.dto.req;

import com.bonobono.backend.community.report.entity.Report;
import com.bonobono.backend.community.report.entity.ReportImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportImageRequestDto {
    private String imageName;
    private String imageUrl;

    @Builder
    public ReportImageRequestDto(String imageName, String imageUrl){
        this.imageName = imageName;
        this.imageUrl = imageUrl;
    }

    public ReportImage toEntity(Report report){
        return ReportImage.builder()
                .imageName(imageName)
                .imageUrl(imageUrl)
                .report(report)
                .build();
    }
}
