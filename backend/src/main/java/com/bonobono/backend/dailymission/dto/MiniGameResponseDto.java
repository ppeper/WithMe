package com.bonobono.backend.dailymission.dto;

import lombok.Getter;

@Getter
public class MiniGameResponseDto {

    private String problem;
    private String answer;

    public MiniGameResponseDto(String problem, String answer) {
        this.problem=problem;
        this.answer=answer;
    }

}
