package com.bonobono.backend.location.dto.req;

import com.bonobono.backend.location.entity.Campaign;
import com.bonobono.backend.location.entity.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CampaignSaveRequestDto {

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean completionStatus;
    private String authority;
    private Long locationId;

    @Builder
    public CampaignSaveRequestDto(String name,
                                  LocalDateTime startDate,
                                  LocalDateTime endDate,
                                  boolean completionStatus,
                                  String authority,
                                  Long locationId){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.completionStatus = completionStatus;
        this.authority =authority;
        this.locationId = locationId;
    }

    public Campaign toEntity(Location location){
        return Campaign.builder()
                .name(name)
                .startDate(startDate)
                .endDate(endDate)
                .authority(authority)
                .location(location)
                .build();
    }


}
