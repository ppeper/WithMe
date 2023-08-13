package com.bonobono.backend.chatting.controller;

import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.chatting.dto.ChatMessageResponseDto;
import com.bonobono.backend.chatting.dto.ChatRoomRequestDto;
import com.bonobono.backend.chatting.dto.ChatRoomResponseDto;
import com.bonobono.backend.chatting.dto.ChatRoomWithMessagesDto;
import com.bonobono.backend.chatting.repository.ChatRoomRepository;
import com.bonobono.backend.chatting.service.ChatMessageService;
import com.bonobono.backend.chatting.service.ChatRoomService;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatting")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageService chatMessageService;
    private final MemberRepository memberRepository;

    /**
     * 맴버정보도 들고오기*/
    @PostMapping("/room")
    public ResponseEntity<ChatRoomWithMessagesDto> makeRoom(@RequestBody ChatRoomRequestDto chatRoomRequestDto) {
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findByRoomNumber(chatRoomRequestDto.getRoomNumber());
        //고유한 값이어야 함

        //채팅방이 이미있으면, 반환
        if (optionalChatRoom.isPresent()) {
            List<ChatMessageResponseDto> chatMessage = chatMessageService.findByRoomNumber(chatRoomRequestDto.getRoomNumber());
            ChatRoom chatRoom = optionalChatRoom.get();
           ChatRoomWithMessagesDto chatRoomWithMessagesDto = new ChatRoomWithMessagesDto(chatRoom,chatMessage);
            return ResponseEntity.ok(chatRoomWithMessagesDto);
        //없으면 새로 만들기
        } else {
            ChatRoomRequestDto requestDto = new ChatRoomRequestDto(chatRoomRequestDto.getNickname(), chatRoomRequestDto.getRoomNumber());
            ChatRoom newChatRoom=chatRoomService.save(requestDto);
            ChatRoomWithMessagesDto chatRoomWithMessagesDto = new ChatRoomWithMessagesDto(newChatRoom, new ArrayList<>());
            return ResponseEntity.ok(chatRoomWithMessagesDto);
        }
    }


    //전체 조회
    @GetMapping("/list")
    public ResponseEntity<List<ChatRoomResponseDto>> findAll() {
        List<ChatRoomResponseDto> roomResponseDtos = chatRoomService.findByList();
        return ResponseEntity.ok(roomResponseDtos);
    }


    //채팅방 삭제
    //채팅방 삭제하면 채팅도 삭제되는지 확인 필요
    @DeleteMapping("/{roomNumber}")
    public ResponseEntity<Void> DeleteChat(@PathVariable int roomNumber) {
        chatRoomService.delete(roomNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
