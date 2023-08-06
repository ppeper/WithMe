package com.bonobono.backend.dailymission.repository;

import com.bonobono.backend.dailymission.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    boolean existsByMemberIdAndCheckDate(Long memberId, LocalDate checkDate);

}
