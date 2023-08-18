package com.bonobono.backend.dailymission.repository;

import com.bonobono.backend.dailymission.domain.MiniGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MiniGameRepository extends JpaRepository<MiniGame, Long> {

}
