package com.bonobono.backend.location.dto.res;

import com.bonobono.backend.location.entity.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocationListResponseDto {

    private String name;

    private double centerLatitude;

    private double centerLongitude;


    public LocationListResponseDto(Location entity){
        this.name = entity.getName();
        this.centerLatitude = entity.getCenterLatitude();
        this.centerLongitude = entity.getCenterLongitude();
    }
}
