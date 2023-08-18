package com.bonobono.backend.chatting.dto;

import com.bonobono.backend.chatting.domain.ChatMessage;
import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.ProfileImg;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

// 채팅방 조회(get)에 사용할 DTO
@Getter
public class ChatRoomResponseDto {
    private int roomNumber;
    private String nickname;
    private String profileImgName;
    private String profileImgUrl;
    private String msg;
    private String messageCreatedDate;

    public ChatRoomResponseDto(ChatRoom chatRoom, String msg, String messageCreatedDate) {
        this.roomNumber = chatRoom.getRoomNumber();
        this.nickname = chatRoom.getOther().getNickname();
        this.profileImgName=chatRoom.getOther().getProfileImg().getImageName();
        this.profileImgUrl=chatRoom.getOther().getProfileImg().getImageUrl();
            this.msg= msg;
            this.messageCreatedDate = messageCreatedDate;
    }
}
