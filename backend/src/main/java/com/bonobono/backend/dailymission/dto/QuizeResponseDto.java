package com.bonobono.backend.dailymission.dto;

import com.bonobono.backend.dailymission.domain.QuizProblemChoice;
import lombok.Getter;

import java.util.List;

@Getter
public class QuizeResponseDto {

    private Long problemId;
    private String problem;
    private String answer;
    private List<QuizProblemChoice> choices;
    private String commentary;

    public QuizeResponseDto(String answer, String problem, Long problemId, List<QuizProblemChoice> choices, String commentary) {
        this.answer=answer;
        this.problemId = problemId;
        this.problem=problem;
        this.choices=choices;
        this.commentary=commentary;

    }
}

