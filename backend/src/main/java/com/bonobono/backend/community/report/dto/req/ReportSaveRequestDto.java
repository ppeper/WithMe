package com.bonobono.backend.community.report.dto.req;

import com.bonobono.backend.community.report.entity.Report;
import com.bonobono.backend.location.entity.Location;
import com.bonobono.backend.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportSaveRequestDto {

    private String title;
    private String content;
    private double latitude;
    private double longitude;
    private Long locationId;


    @Builder
    public ReportSaveRequestDto(String title,
                                 String content,
                                 double latitude,
                                 double longitude,
                                Long locationId){
        this.title = title;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationId = locationId;
    }

    public Report toEntity(Member member, Location location){
        return Report.builder()
                .title(title)
                .content(content)
                .latitude(latitude)
                .longitude(longitude)
                .member(member)
                .location(location)
                .build();

    }
}
