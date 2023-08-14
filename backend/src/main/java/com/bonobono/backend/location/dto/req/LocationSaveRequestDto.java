package com.bonobono.backend.location.dto.req;

import com.bonobono.backend.location.entity.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocationSaveRequestDto {

    private String name;
    private double centerLatitude;
    private double centerLongitude;
//    private double leftLatitude;
    private double leftLongitude;
    private double upperLatitude;
    private double rightLongitude;
    private double lowerLatitude;


    @Builder
    public LocationSaveRequestDto(String name,
                                  double centerLatitude,
                                  double centerLongitude,
                                  double upperLatitude,
                                  double leftLongitude,
                                  double lowerLatitude,
                                  double rightLongitude) {
        this.name = name;
        this.centerLatitude = centerLatitude;
        this.centerLongitude = centerLongitude;
        this.upperLatitude = upperLatitude;
        this.leftLongitude = leftLongitude;
        this.lowerLatitude = lowerLatitude;
        this.rightLongitude = rightLongitude;
    }

    public Location toEntity(){
        return Location.builder()
                .name(name)
                .centerLatitude(centerLatitude)
                .centerLongitude(centerLongitude)
                .lowerLatitude(lowerLatitude)
                .leftLongitude(leftLongitude)
                .upperLatitude(upperLatitude)
                .rightLongitude(rightLongitude)
                .build();
    }
}
