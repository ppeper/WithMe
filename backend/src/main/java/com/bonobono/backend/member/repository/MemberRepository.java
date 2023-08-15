package com.bonobono.backend.member.repository;

import com.bonobono.backend.member.domain.enumtype.Role;
import org.springframework.stereotype.Repository;
import com.bonobono.backend.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long id);
    Optional<Member> findByUsername(String username);
    Optional<Member> findByNickname(String nickname);
}