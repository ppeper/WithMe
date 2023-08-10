package com.bonobono.backend.dailymission.dto;

import com.bonobono.backend.dailymission.domain.QuizProblemChoice;
import lombok.Getter;

import java.util.List;

@Getter
public class OXQuizResponseDto {

    private Long problemId;
    private String problem;
    private String answer;
    private String commentary;

    public OXQuizResponseDto(String answer, String problem, Long problemId, String commentary) {
        this.answer=answer;
        this.problemId = problemId;
        this.problem=problem;
        this.commentary=commentary;

    }
}

