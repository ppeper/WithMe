package com.bonobono.backend.chatting.repository;

import com.bonobono.backend.chatting.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findByRoomName(String roomName);

}
