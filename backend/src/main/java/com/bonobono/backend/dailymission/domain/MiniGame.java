package com.bonobono.backend.dailymission.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Table(name="Minigame")
@NoArgsConstructor
public class MiniGame {
    //미니게임은 map으로, 문제와 정답 연결시켜줄까..?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String problem;

    private String answer;

    @Builder
    public MiniGame(String problem, String answer) {
        this.problem=problem;
        this.answer=answer;
    }

    public boolean checkAnswer(String answer) {
        return this.answer.equals(answer);
    }
}
