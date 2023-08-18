package com.bonobono.backend.fcm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FCMReportNotificationRequestDto {
    private Long memberId;
    private String title;
    private String body;
    private Long reportId;

    @Builder
    public FCMReportNotificationRequestDto(Long memberId, String title, String body, Long reportId){
        this.memberId = memberId;
        this.title = title;
        this.body = body;
        this.reportId = reportId;
    }
}
