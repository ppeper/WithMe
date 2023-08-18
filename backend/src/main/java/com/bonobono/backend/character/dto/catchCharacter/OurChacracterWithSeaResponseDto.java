package com.bonobono.backend.character.dto.catchCharacter;


import com.bonobono.backend.character.domain.LocationOurCharacter;
import com.bonobono.backend.character.domain.OurCharacter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OurChacracterWithSeaResponseDto {
    private double charLatitude;
    private double charLongtitude;
    private OurCharacter ourCharacter;
    private Long locationCharId;
    private String locationName;

    public OurChacracterWithSeaResponseDto(double charLatitude, double charLongtitude, LocationOurCharacter locationOurCharacter) {
        this.charLatitude = charLatitude;
        this.charLongtitude = charLongtitude;
        this.locationCharId = locationOurCharacter.getId();
        this.ourCharacter = locationOurCharacter.getOurCharacter();
        this.locationName = locationOurCharacter.getLocation().getName();

    }


}
