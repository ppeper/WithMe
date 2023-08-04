package com.bonobono.backend.dailymission.repository;

import com.bonobono.backend.dailymission.domain.IsMiniGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface IsMiniGameRepository extends JpaRepository<IsMiniGame, Long> {

    boolean findByCheckDateAndMember(Long id, LocalDate checkDate);
}
