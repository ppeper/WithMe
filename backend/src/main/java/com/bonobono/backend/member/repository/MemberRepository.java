package com.bonobono.backend.member.repository;

import com.bonobono.backend.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByAccountId(String accountId);
    Optional<Member> findByPhoneNumber(String phoneNumber);
}
