package com.bonobono.backend.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequestDto {

    private Long memberId;

    @Builder
    public MemberRequestDto(Long memberId){
        this.memberId = memberId;
    }

}
