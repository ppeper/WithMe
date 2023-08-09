package com.bonobono.backend.character.dto.catchCharacter;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NowPositionRequestDto {
    //현재 위치 정보(위경도 값을 줘.../ 아님 name을 줘..
    private String name;
    private double latitude;
    private double longitude;

    @Builder
    public NowPositionRequestDto(String name, Double latitude, Double longitude) {
        this.name=name;
        this.latitude=latitude;
        this.longitude=longitude;
    }
}
