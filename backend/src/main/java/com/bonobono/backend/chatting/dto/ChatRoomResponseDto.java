package com.bonobono.backend.chatting.dto;

import com.bonobono.backend.chatting.domain.ChatRoom;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

// 채팅방 조회(get)에 사용할 DTO
@Getter
public class ChatRoomResponseDto {
    private Long id;
    private String roomname;
    private String createdDate;
    private String updatedDate;
    private List<ChatMessageResponseDto> messages;

    public ChatRoomResponseDto(ChatRoom chatRoom) {
        this.id = chatRoom.getId();
        this.roomname = chatRoom.getRoomName();
        this.createdDate = chatRoom.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        this.updatedDate = chatRoom.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        this.messages = chatRoom.getChatMessageList().stream().map(ChatMessageResponseDto::new).collect(Collectors.toList());
    }
}
