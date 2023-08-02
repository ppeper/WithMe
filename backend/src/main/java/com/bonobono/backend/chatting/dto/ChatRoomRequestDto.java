package com.bonobono.backend.chatting.dto;

import com.bonobono.backend.chatting.domain.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//채팅방 Create하기 위한 dto
@Getter
@NoArgsConstructor
public class ChatRoomRequestDto {
    private String other;
    private String roomNumber;

    @Builder
    public ChatRoomRequestDto(String other, String roomNumber) {
        this.other=other;
        this.roomNumber=roomNumber;
    }

    //채팅방이름으로 chatRoom객체 만들기(사용자 지정으로, username으로 클라이언트가 요청)
    public ChatRoom toEntity() {
        return ChatRoom.builder()
                .other(this.other)
                .roomNumber(this.roomNumber)
                .build();
    }
}