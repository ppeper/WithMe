package com.bonobono.backend.dailymission.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizRequestDto {

    private String answer;
    private Long problemId;
    private Long memberId;

    public QuizRequestDto(String answer, Long problemId, Long memberId) {
        this.answer=answer;
        this.problemId=problemId;
        this.memberId=memberId;
    }

}
