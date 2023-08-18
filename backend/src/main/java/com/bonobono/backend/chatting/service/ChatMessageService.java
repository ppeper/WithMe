package com.bonobono.backend.chatting.service;

import com.bonobono.backend.chatting.domain.ChatMessage;
import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.chatting.dto.ChatMessageRequestDto;
import com.bonobono.backend.chatting.dto.ChatMessageResponseDto;
import com.bonobono.backend.chatting.dto.ChatRoomResponseDto;
import com.bonobono.backend.chatting.mongo.ChatMessageRepository;
import com.bonobono.backend.global.util.SecurityUtil;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void save(final ChatMessageRequestDto requestDto) {
        ChatMessage chatMessage=requestDto.toEntity();
        chatMessageRepository.save(chatMessage);
    }

    @Transactional
    public List<ChatMessageResponseDto> findRoom(int roomNumber) {

        List<ChatMessage> chatMessage = this.chatMessageRepository.findByRoomNumber(roomNumber);

        List<ChatMessageResponseDto> dtolist = new ArrayList<>();
        for (ChatMessage chatMessage1 : chatMessage) {
            ChatMessageResponseDto responseDto = new ChatMessageResponseDto(chatMessage1);
            dtolist.add(responseDto);
        }
        return dtolist;
    }



}