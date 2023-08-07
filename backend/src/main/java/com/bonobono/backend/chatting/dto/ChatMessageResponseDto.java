package com.bonobono.backend.chatting.dto;

import com.bonobono.backend.chatting.domain.ChatMessage;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Getter
public class ChatMessageResponseDto {
    private String userName;
    private String msg;
    private String imageUrl;
    private String roomNumber;
    private String sessionId;
    private LocalDateTime createdDate;
//    private LocalDateTime updatedDate;



    public ChatMessageResponseDto(ChatMessage chatMessage) {
        this.userName = chatMessage.getUserName();
        this.msg = chatMessage.getMsg();
        this.imageUrl = chatMessage.getImageUrl();
        this.roomNumber=chatMessage.getRoomNumber();
        this.sessionId=chatMessage.getSessionId();
        this.createdDate=chatMessage.getCreatedTime();
//        this.updatedDate=chatMessage.getUpdatedDate();
    }

}
