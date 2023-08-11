package com.bonobono.backend.location.dto.res;

import com.bonobono.backend.location.entity.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocationSelectResponseDto {
    private Long locationId;
    private String name;

    public LocationSelectResponseDto(Location entity){
        this.locationId = entity.getId();
        this.name = entity.getName();
    }
}
