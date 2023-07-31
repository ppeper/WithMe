package com.bonobono.backend.chatting.service;

import com.bonobono.backend.chatting.domain.ChatMessage;
import com.bonobono.backend.chatting.dto.ChatMessageRequestDto;
import com.bonobono.backend.chatting.dto.ChatMessageResponseDto;
import com.bonobono.backend.chatting.mongo.ChatMessageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageService {

    private ChatMessageRepository chatMessageRepository;

    public String save(final ChatMessageRequestDto requestDto) {
        ChatMessage chatMessage=requestDto.toEntity();
        return this.chatMessageRepository.save(chatMessage).getId();
    }



}