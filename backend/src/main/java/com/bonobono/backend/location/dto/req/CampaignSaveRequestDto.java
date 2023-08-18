package com.bonobono.backend.location.dto.req;

import com.bonobono.backend.location.entity.Campaign;
import com.bonobono.backend.location.entity.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class CampaignSaveRequestDto {

    private String name;
    private String startDate;
    private String endDate;
    private boolean completionStatus;
    private String authority;
    private String url;
    private Long locationId;

    @Builder
    public CampaignSaveRequestDto(String name,
                                  String startDate,
                                  String endDate,
                                  boolean completionStatus,
                                  String authority,
                                  String url,
                                  Long locationId){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.completionStatus = completionStatus;
        this.authority = authority;
        this.url = url;
        this.locationId = locationId;
    }

    public Campaign toEntity(Location location){
        return Campaign.builder()
                .name(name)
                .startDate(LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yy.MM.dd")))
                .endDate(LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yy.MM.dd")))
                .authority(authority)
                .url(url)
                .location(location)
                .build();
    }


}
