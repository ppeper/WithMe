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
    private String sessionId; //sessionid(
    private String userName; //username;
    private String msg; //msg
    private String imageUrl; // file(BYTE형식으로 변환?)
    private int roomNumber;


    //생성자
    @Builder
    public ChatMessageRequestDto(String userName, String sessionId, String msg, String imageUrl, int roomNumber) {
        this.msg = msg;
        this.userName = userName;
        this.sessionId = sessionId;
        this.imageUrl = imageUrl;
        this.roomNumber=roomNumber;

    }
    //객체 만들기
    public ChatMessage toEntity() {
        return ChatMessage.builder()
                .userName(userName)
                .msg(msg)
                .imageUrl(imageUrl)
                .roomNumber(roomNumber)
                .sessionId(sessionId)
                .build();
    }
}
