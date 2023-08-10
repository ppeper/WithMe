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
    private double leftLatitude;
    private double leftLongitude;
    private double rightLatitude;
    private double rightLongitude;


    @Builder
    public LocationSaveRequestDto(String name,
                                  double centerLatitude,
                                  double centerLongitude,
                                  double leftLatitude,
                                  double leftLongitude,
                                  double rightLatitude,
                                  double rightLongitude) {
        this.name = name;
        this.centerLatitude = centerLatitude;
        this.centerLongitude = centerLongitude;
        this.leftLatitude = leftLatitude;
        this.leftLongitude = leftLongitude;
        this.rightLatitude = rightLatitude;
        this.rightLongitude = rightLongitude;
    }

    public Location toEntity(){
        return Location.builder()
                .name(name)
                .centerLatitude(centerLatitude)
                .centerLongitude(centerLongitude)
                .leftLatitude(leftLatitude)
                .leftLongitude(leftLongitude)
                .rightLatitude(rightLatitude)
                .rightLongitude(rightLongitude)
                .build();
    }
}
