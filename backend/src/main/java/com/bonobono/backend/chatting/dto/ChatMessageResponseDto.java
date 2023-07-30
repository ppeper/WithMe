package com.bonobono.backend.chatting.dto;

import com.bonobono.backend.chatting.domain.ChatMessage;
import lombok.Data;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
public class ChatMessageResponseDto {
    private String id;
    private String sender;
    private String message;
//    private String imgUrl;
    private Date createdDate;
    private Date updatedDate;

    public ChatMessageResponseDto(ChatMessage chatMessage) {
        this.id = chatMessage.getId();
        this.sender = chatMessage.getSender();
        this.message = chatMessage.getMessage();
//        this.imgUrl= chatMessage.getImgUrl();
        this.createdDate = chatMessage.getCreatedTime();
        this.updatedDate = chatMessage.getUpdatedTime();
    }
}
