package com.bonobono.backend.chatting.dto;

import com.bonobono.backend.chatting.domain.ChatRoom;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

// 채팅방 조회(get)에 사용할 DTO
@Getter
public class ChatRoomResponseDto {
    private String roomNumber;
    private String other;
    private String createdDate;
    private String updatedDate;
//    private List<ChatMessageResponseDto> messages;


    public ChatRoomResponseDto(ChatRoom chatRoom) {
        this.roomNumber = chatRoom.getRoomNumber();
        this.other = chatRoom.getOther();
        this.createdDate = chatRoom.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        this.updatedDate = chatRoom.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
//        this.messages = chatRoom.getChatMessageList().stream().map(ChatMessageResponseDto::new).collect(Collectors.toList());
    }

}
