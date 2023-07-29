package com.bonobono.backend.chatting.service;

import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.chatting.dto.ChatRoomRequestDto;
import com.bonobono.backend.chatting.dto.ChatRoomResponseDto;
import com.bonobono.backend.chatting.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;// 채팅방조회

    //채팅방 인덱스로 검색하기
    @Transactional(readOnly = true)
    public ChatRoomResponseDto findById(final Long id) {
        ChatRoom chatRoom = this.chatRoomRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 chatroom이 존재하지 않습니다. id="+id)
        );
        return new ChatRoomResponseDto(chatRoom);
    }

    //채팅방 조회(이름으로 검색)
    @Transactional
    public List<ChatRoomResponseDto> findByRoomName(String roomName) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<ChatRoom> chatRoomList = this.chatRoomRepository.findByRoomName(roomName);
        return chatRoomList.stream().map(ChatRoomResponseDto::new).collect(Collectors.toList());
    }

    //전체 채팅방 목록 조회
    @Transactional(readOnly = true)
    public List<ChatRoomResponseDto> findByList() {
        List<ChatRoom> lst = chatRoomRepository.findAll();
        List<ChatRoomResponseDto> dtolist = new ArrayList<>();

        for (ChatRoom chatRoom : lst) {
            ChatRoomResponseDto responseDto = new ChatRoomResponseDto(chatRoom);
            dtolist.add(responseDto);
        }
        return dtolist;
    }



    //채팅방 개설
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
