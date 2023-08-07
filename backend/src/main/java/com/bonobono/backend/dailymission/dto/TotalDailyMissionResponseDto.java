package com.bonobono.backend.dailymission.dto;

import lombok.Getter;

@Getter
public class TotalDailyMissionResponseDto {
    private int AttendanceScore;
    private int TotalScore;

    public TotalDailyMissionResponseDto(int AttendanceScore, int TotalScore) {
        this.AttendanceScore=AttendanceScore;
        this.TotalScore=TotalScore;
    }


}
