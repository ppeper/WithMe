package com.bonobono.backend.dailymission.repository;

import com.bonobono.backend.dailymission.domain.Quiz;
import com.bonobono.backend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    boolean existsByMemberIdAndCheckDate(Long memberId, LocalDate checkDate);

    List<Quiz> findQuizByMemberAndCheckDateBetween(Member member, LocalDate startDate, LocalDate endDate);
}
