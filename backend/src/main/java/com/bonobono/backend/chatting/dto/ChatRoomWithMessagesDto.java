package com.bonobono.backend.chatting.dto;

import com.bonobono.backend.chatting.domain.ChatMessage;
import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.ProfileImg;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatRoomWithMessagesDto {
    private String nickname;
    private int roomNumber;
    private String profileImgName;
    private String profileImgUrl;
    private List<ChatMessageResponseDto> messages;

    public ChatRoomWithMessagesDto(ChatRoom chatRoom, List<ChatMessageResponseDto> messages) {
        this.nickname = chatRoom.getOther().getNickname();
        this.profileImgName= chatRoom.getOther().getProfileImg().getImageName();
        this.profileImgUrl = chatRoom.getOther().getProfileImg().getImageUrl();
        this.roomNumber = chatRoom.getRoomNumber();
        this.messages = messages;
    }
}
