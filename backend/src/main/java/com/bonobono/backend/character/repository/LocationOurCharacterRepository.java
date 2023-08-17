package com.bonobono.backend.character.repository;

import com.bonobono.backend.character.domain.LocationOurCharacter;
import com.bonobono.backend.character.domain.OurCharacter;
import com.bonobono.backend.location.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationOurCharacterRepository extends JpaRepository<LocationOurCharacter,Long> {
    List<LocationOurCharacter> findByLocation_id(Long locationId);

    LocationOurCharacter findByLocationAndOurCharacter(Location location, OurCharacter ourCharacter);
}
