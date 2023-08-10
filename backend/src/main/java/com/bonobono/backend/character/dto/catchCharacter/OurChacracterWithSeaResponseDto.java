package com.bonobono.backend.character.dto.catchCharacter;


import com.bonobono.backend.character.domain.LocationOurCharacter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OurChacracterWithSeaResponseDto {
    private double charLatitude;
    private double charLongtitude;
    private LocationOurCharacter locationOurCharacter; //여기의 id가 ourcharacter의 id

    public OurChacracterWithSeaResponseDto(double charLatitude, double charLongtitude, LocationOurCharacter locationOurCharacter) {
        this.charLatitude = charLatitude;
        this.charLongtitude = charLongtitude;
        this.locationOurCharacter = locationOurCharacter;
    }


}
