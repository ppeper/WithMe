package com.bonobono.backend.dailymission.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttendanceDto {
    //requestdto역할
    private Long memberId;
    private LocalDate now;

    public AttendanceDto(Long memberId) {
        this.memberId=memberId;
        this.now=LocalDate.now();
    }

}
