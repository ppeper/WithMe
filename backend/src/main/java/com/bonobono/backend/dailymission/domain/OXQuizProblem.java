package com.bonobono.backend.dailymission.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ox_quiz_problem")
@NoArgsConstructor
@Getter
public class OXQuizProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String problem;

    private String answer;

    private String commentary;

    public boolean checkAnswer(String answer) {
        return this.answer.equals(answer);
    }


}