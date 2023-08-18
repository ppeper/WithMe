package com.bonobono.backend.character.repository;

import com.bonobono.backend.character.domain.OurCharacter;
import com.bonobono.backend.character.enumClass.CharacterLevelEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface OurCharacterRepository extends JpaRepository<OurCharacter, Long> {
    @Query("SELECT oc FROM OurCharacter oc WHERE oc.id NOT IN (SELECT uc.ourCharacter.id FROM UserCharacter uc WHERE uc.member.id = :memberId) AND oc.level = 'LEVEL_1'")
    List<OurCharacter> findNotLinkedOurCharactersByMemberAndLevel(@Param("memberId") Long memberId);

    OurCharacter findByCharOrdIdAndLevel(Long charOrdId, CharacterLevelEnum newLevel);
}
