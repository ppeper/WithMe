package com.bonobono.backend.chatting.dto;

import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.ProfileImg;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

// 채팅방 조회(get)에 사용할 DTO
@Getter
public class ChatRoomResponseDto {
    private int roomNumber;
    private String nickname;
    private String createdDate;
    private ProfileImg profileImg;

    public ChatRoomResponseDto(ChatRoom chatRoom) {
        this.roomNumber = chatRoom.getRoomNumber();
        this.nickname = chatRoom.getOther().getNickname();
        this.profileImg=chatRoom.getOther().getProfileImg();
        this.createdDate = chatRoom.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
//        this.updatedDate = chatRoom.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
//        this.messages = chatRoom.getChatMessageList().stream().map(ChatMessageResponseDto::new).collect(Collectors.toList());
    }

}
