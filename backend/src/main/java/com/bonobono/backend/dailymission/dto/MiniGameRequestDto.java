package com.bonobono.backend.dailymission.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MiniGameRequestDto {

    private String answer;
    private Long problemId;
    private Long memberId;

    public MiniGameRequestDto(String answer, Long problemId, Long memberId) {
        this.answer=answer;
        this.problemId=problemId;
        this.memberId=memberId;
    }

}
