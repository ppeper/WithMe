package com.bonobono.backend.dailymission.dto;

import lombok.Getter;

@Getter
public class MiniGameResponseDto {

    private String problem;
    private String answer;
    private boolean participated;

    public MiniGameResponseDto(String problem, String answer, boolean participated) {
        this.problem=problem;
        this.answer=answer;
        this.participated=participated;
    }

}
