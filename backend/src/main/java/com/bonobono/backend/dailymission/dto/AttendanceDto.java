package com.bonobono.backend.dailymission.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttendanceDto {
    //requestdto역할
    private Long memberId;

    public AttendanceDto(Long memberId) {
        this.memberId=memberId;
    }

}
