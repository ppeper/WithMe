package com.bonobono.backend.chatting.repository;

import com.bonobono.backend.chatting.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    // 채팅방 조회 - RoomName검색, 정확히 일치
    ChatRoom findByRoomName(String roomName);
    // 전체 채팅방 목록 조회
    List<ChatRoom> findAllByRoomName(String roomName);
}
