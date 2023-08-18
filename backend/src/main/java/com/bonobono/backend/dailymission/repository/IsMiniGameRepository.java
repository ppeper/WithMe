package com.bonobono.backend.dailymission.repository;

import com.bonobono.backend.dailymission.domain.IsMiniGame;
import com.bonobono.backend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IsMiniGameRepository extends JpaRepository<IsMiniGame, Long> {

    List<IsMiniGame> findIsMiniGameByMemberAndCheckDateBetween(Member member, LocalDate startDate, LocalDate endDate);
}
