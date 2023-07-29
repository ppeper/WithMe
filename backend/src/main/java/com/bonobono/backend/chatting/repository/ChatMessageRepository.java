package com.bonobono.backend.chatting.repository;

import com.bonobono.backend.chatting.domain.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
}
