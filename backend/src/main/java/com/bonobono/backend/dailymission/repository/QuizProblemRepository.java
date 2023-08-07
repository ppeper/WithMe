package com.bonobono.backend.dailymission.repository;


import com.bonobono.backend.dailymission.domain.QuizProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizProblemRepository  extends JpaRepository<QuizProblem, Long> {
}
