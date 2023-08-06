package com.bonobono.backend.character.repository;

import com.bonobono.backend.character.domain.OurCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OurCharacterRepository extends JpaRepository<OurCharacter, Long> {
    @Query("SELECT oc FROM OurCharacter oc WHERE oc.id NOT IN (SELECT uc.ourCharacter.id FROM UserCharacter uc WHERE uc.member.id = :memberId)")
    List<OurCharacter> findNotLinkedOurCharactersByMember(@Param("memberId")Long memberId);

}
