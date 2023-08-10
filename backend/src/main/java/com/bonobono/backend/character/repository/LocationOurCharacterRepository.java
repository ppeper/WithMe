package com.bonobono.backend.character.repository;

import com.bonobono.backend.character.domain.LocationOurCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationOurCharacterRepository extends JpaRepository<LocationOurCharacter,Long> {
    List<LocationOurCharacter> findByLocation_id(Long locationId);

}
