package com.bonobono.backend.community.report.dto.res;

import com.bonobono.backend.community.report.entity.ReportImage;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportImageResponseDto {

    private String imageName;
    private String imageUrl;

    public ReportImageResponseDto(ReportImage entity){
        this.imageName = entity.getImageName();
        this.imageUrl = entity.getImageUrl();
    }
}
