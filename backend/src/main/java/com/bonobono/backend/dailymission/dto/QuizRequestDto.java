package com.bonobono.backend.dailymission.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizRequestDto {
    private String problem;
    private String answer;
    private Long memberId;

    public QuizRequestDto(String problem, String answer,  Long memberId) {
        this.problem=problem;
        this.answer=answer;
        this.memberId=memberId;
    }

}
