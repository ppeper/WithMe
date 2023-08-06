package com.bonobono.backend.dailymission.repository;

import com.bonobono.backend.dailymission.domain.OXQuizProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OXQuizProblemRepository extends JpaRepository<OXQuizProblem, Long> {
    Optional<OXQuizProblem> findByProblem(String problem);
}
