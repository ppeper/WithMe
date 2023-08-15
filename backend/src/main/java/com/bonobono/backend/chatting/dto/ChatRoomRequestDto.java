package com.bonobono.backend.chatting.dto;

import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//채팅방 Create하기 위한 dto
@Getter
@NoArgsConstructor
public class ChatRoomRequestDto {
    private String nickname;

    @Builder
    public ChatRoomRequestDto(String nickname) {
        this.nickname=nickname;
    }


    //채팅방이름으로 chatRoom객체 만들기(사용자 지정으로, username으로 클라이언트가 요청)
//    public ChatRoom toEntity(Member member, Member other) {
//        return ChatRoom.builder()
//                .other(other)
//                .member(member)
//                .build();
//    }
}
