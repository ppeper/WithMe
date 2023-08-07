package com.bonobono.backend.dailymission.repository;

import com.bonobono.backend.dailymission.domain.OXQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface OXQuizRepository extends JpaRepository<OXQuiz, Long> {

    boolean existsByMemberIdAndCheckDate(Long memberId, LocalDate checkDate);
}
