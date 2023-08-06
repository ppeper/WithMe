package com.bonobono.backend.dailymission.dto;

import lombok.Getter;

@Getter
public class QuizeResponseDto {

    private String problem;
    private String answer;
    private String choices;
    private String commentary;

    public QuizeResponseDto(String answer, String problem, String choices, String commentary) {
        this.answer=answer;
        this.problem=problem;
        this.choices=choices;
        this.commentary=commentary;

    }
}

