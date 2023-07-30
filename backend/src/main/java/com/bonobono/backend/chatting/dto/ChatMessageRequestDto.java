package com.bonobono.backend.chatting.dto;

import com.bonobono.backend.chatting.domain.ChatMessage;
import com.bonobono.backend.chatting.domain.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//채팅 메시지 create
//발송자, 메시지, 채팅방 정보가 담겨 요청
@Getter
@Setter
@NoArgsConstructor
public class ChatMessageRequestDto {
    private String sender;
    private String message;
    private ChatRoom chatRoom; //채팅방 정보
    private String imgUrl;

    //생성자
    @Builder
    public ChatMessageRequestDto(String sender, String message) {
        this.sender = sender;
        this.message = message;
//        this.chatRoom = chatRoom;
//        this.imgUrl = imgUrl;
    }
    //객체 만들기
    public ChatMessage toEntity() {
        return ChatMessage.builder()
                .sender(sender)
                .message(message)
//                .chatRoom(chatRoom)
//                .imgUrl(imgUrl)
                .build();
    }
}
