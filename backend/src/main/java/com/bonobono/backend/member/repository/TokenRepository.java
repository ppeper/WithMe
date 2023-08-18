package com.bonobono.backend.member.repository;

import com.bonobono.backend.member.domain.Token;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {

    Optional<Token> findByKey(String key);
    Optional<Token> deleteByKey(String key);
    long countByValue(String jwt);
}