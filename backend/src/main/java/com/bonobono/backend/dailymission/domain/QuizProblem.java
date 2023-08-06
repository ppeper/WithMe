package com.bonobono.backend.dailymission.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="quiz_problem")
@NoArgsConstructor
@Getter
public class QuizProblem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String problem;

    private String answer;

    private String commentary;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quizProblem")
    private List<QuizProblemChoice> choices;


    @Builder
    public QuizProblem(String problem, String answer, String commentary) {
        this.answer=answer;
        this.problem=problem;
        this.commentary=commentary;
    }

    public boolean checkAnswer(String answer) {
        return this.answer.equals(answer);
    }


}