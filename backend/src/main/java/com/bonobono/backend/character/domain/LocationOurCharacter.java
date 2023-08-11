package com.bonobono.backend.character.domain;

import com.bonobono.backend.location.entity.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class LocationOurCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "our_character_id")
    private OurCharacter ourCharacter;

    @Builder
    public LocationOurCharacter(Location location, OurCharacter ourCharacter) {
        this.location = location;
        this.ourCharacter=ourCharacter;
    }
}
