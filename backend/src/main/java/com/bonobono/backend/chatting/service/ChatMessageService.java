package com.bonobono.backend.chatting.service;

import com.bonobono.backend.chatting.dto.ChatMessageRequestDto;
import com.bonobono.backend.chatting.dto.ChatRoomRequestDto;
import com.bonobono.backend.chatting.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChatMessageService {
    //메시지 저장
    private ChatMessageRepository chatMessageRepository;

    public String save(final ChatMessageRequestDto requestDto) {
        return this.chatMessageRepository.save(requestDto.toEntity()).getId();
    }


}
