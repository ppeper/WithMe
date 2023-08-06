package com.bonobono.backend.member.repository;

import com.bonobono.backend.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
