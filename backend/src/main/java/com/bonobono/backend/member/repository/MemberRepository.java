package com.bonobono.backend.member.repository;

import com.bonobono.backend.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByAccountId(String accountId);

    Optional<Member> findByNickname(String nickname);

    @Query("SELECT m.refreshToken FROM Member m WHERE m.id=:id")
    String getRefreshTokenById(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Member m SET m.refreshToken=:token WHERE m.id=:id")
    void updateRefreshToken(@Param("id") Integer id, @Param("token") String token);
}
