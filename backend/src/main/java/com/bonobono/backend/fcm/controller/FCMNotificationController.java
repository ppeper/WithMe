package com.bonobono.backend.fcm.controller;

import com.bonobono.backend.fcm.dto.FCMNotificationRequestDto;
import com.bonobono.backend.fcm.service.FCMNotificationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notification")
public class FCMNotificationController {

    private final FCMNotificationService fcmNotificationService;

    @Operation(summary = "FCM 알림 보내기")
    @PostMapping("")
    public String sendNotificationByToken(@RequestBody FCMNotificationRequestDto requestDto){
        return fcmNotificationService.sendNotificationByToken(requestDto);
    }
}
