package com.bonobono.backend.dailymission.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizRequestDto {

    private String answer;
    private Long problemId;

    public QuizRequestDto(String answer, Long problemId) {
        this.answer=answer;
        this.problemId=problemId;
    }

}
