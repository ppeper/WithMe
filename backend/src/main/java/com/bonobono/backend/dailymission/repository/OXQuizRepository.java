package com.bonobono.backend.dailymission.repository;

import com.bonobono.backend.dailymission.domain.OXQuiz;
import com.bonobono.backend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OXQuizRepository extends JpaRepository<OXQuiz, Long> {

    boolean existsByMemberIdAndCheckDate(Long memberId, LocalDate checkDate);

    List<OXQuiz> findOXQuizByMemberAndCheckDateBetween(Member member, LocalDate startDate, LocalDate endDate);
}
