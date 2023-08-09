package com.bonobono.backend.fcm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FCMNotificationRequestDto {
    private Long memberId;
    private String title;
    private String body;

    @Builder
    public FCMNotificationRequestDto(Long memberId, String title, String body){
        this.memberId = memberId;
        this.title = title;
        this.body = body;
    }
}
