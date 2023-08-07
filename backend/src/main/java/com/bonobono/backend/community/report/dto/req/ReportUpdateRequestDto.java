package com.bonobono.backend.community.report.dto.req;

import com.bonobono.backend.community.report.entity.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportUpdateRequestDto {

    private String title;
    private String content;
    private double latitude;
    private double longitude;

    @Builder
    public ReportUpdateRequestDto(String title,
                                  String content,
                                  double latitude,
                                  double longitude) {
        this.title = title;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Report toEntity() {
        return Report.builder()
                .title(title)
                .content(content)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
