package com.bonobono.backend.dailymission.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MiniGameRequestDto {

    private String problem;
    private String answer;
    private Long memberId;

    public MiniGameRequestDto(String problem, String answer,  Long memberId) {
        this.problem=problem;
        this.answer=answer;
        this.memberId=memberId;
    }

}
