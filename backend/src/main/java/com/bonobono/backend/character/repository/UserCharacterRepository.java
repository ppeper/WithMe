package com.bonobono.backend.character.repository;

import com.bonobono.backend.character.domain.UserCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCharacterRepository extends JpaRepository<UserCharacter, Long> {
    List<UserCharacter> findByMemberId(Long memberId);

    List<UserCharacter> findByMemberIdAndMain(Long id, Boolean main);
}
