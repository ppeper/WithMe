package com.bonobono.backend.character.repository;

import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.character.enumClass.CharacterLevelEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserCharacterRepository extends JpaRepository<UserCharacter, Long> {
    List<UserCharacter> findByMemberId(Long memberId);

    List<UserCharacter> findByMemberIdAndMain(Long id, Boolean main);

    Optional<UserCharacter> findByMemberIdAndId(Long memberId, Long characterId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE UserCharacter uc SET uc.ourCharacter = (SELECT oc FROM OurCharacter oc WHERE oc.name = :name AND oc.level = :level) WHERE uc.id = :id")
    void upgradeCharacter(@Param("id") Long id, @Param("name") String name, @Param("level") CharacterLevelEnum level);

}
