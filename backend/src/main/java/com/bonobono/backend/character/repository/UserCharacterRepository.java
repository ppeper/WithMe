package com.bonobono.backend.character.repository;

import com.bonobono.backend.character.domain.UserCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCharacterRepository extends JpaRepository<UserCharacter, Long> {
    List<UserCharacter> findByMemberId(Long memberId);
}
