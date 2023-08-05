package com.bonobono.backend.member.repository;

import com.bonobono.backend.member.domain.RefreshToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    Optional<RefreshToken> findByKey(String key);
    Optional<RefreshToken> deleteByKey(String key);

}
