package com.bonobono.backend.dailymission.dto;

import lombok.Getter;

@Getter
public class MiniGameResponseDto {

    private String problem;
    private String answer;
    private Long problemId;

    public MiniGameResponseDto(String problem, String answer, Long problemId) {
        this.problem=problem;
        this.answer=answer;
        this.problemId=problemId;
    }

}
