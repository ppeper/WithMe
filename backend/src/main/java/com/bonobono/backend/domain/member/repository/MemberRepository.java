package com.bonobono.backend.domain.member.repository;

import com.bonobono.backend.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
