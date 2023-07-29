package com.bonobono.backend.chatting.service;

import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.chatting.dto.ChatRoomRequestDto;
import com.bonobono.backend.chatting.dto.ChatRoomResponseDto;
import com.bonobono.backend.chatting.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;// 채팅방조회

    //채팅방 조회
    @Transactional(readOnly = true)
    public ChatRoomResponseDto findById(final Long id) {
        ChatRoom chatRoom = this.chatRoomRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 chatroom이 존재하지 않습니다. id="+id)
        );
        return new ChatRoomResponseDto(chatRoom);
    }

    // 채팅방 저장(개설)
    //채팅방을 개설
    @Transactional
    public Long save(final ChatRoomRequestDto requestDto) {
        return this.chatRoomRepository.save(requestDto.toEntity()).getId();
    }

    //채팅방 삭제
    @Transactional
    public void delete(final Long id) {
        ChatRoom chatRoom = this.chatRoomRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 chatroom이 존재하지 않습니다 +id"+id));
        this.chatRoomRepository.delete(chatRoom);
    }
}
