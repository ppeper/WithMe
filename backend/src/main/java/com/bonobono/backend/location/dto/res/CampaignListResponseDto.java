package com.bonobono.backend.location.dto.res;

import com.bonobono.backend.location.entity.Campaign;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CampaignListResponseDto {

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean completionStatus;

    private String authority;

    private String url;

    private String locationName;

    public CampaignListResponseDto(Campaign entity){
        this.name = entity.getName();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.completionStatus = entity.isCompletionStatus();
        this.authority = entity.getAuthority();
        this.url = entity.getUrl();
        this.locationName = entity.getLocation().getName();

    }
}
