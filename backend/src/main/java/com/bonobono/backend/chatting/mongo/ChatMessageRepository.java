package com.bonobono.backend.chatting.mongo;

import com.bonobono.backend.chatting.domain.ChatMessage;
import com.bonobono.backend.chatting.domain.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findByRoomNumber(int roomNumber);

    ChatMessage findTopByRoomNumberOrderByCreatedTimeDesc(int roomNumber);

    List<ChatMessage> findAllByRoomNumber(int roomNumber);
}
