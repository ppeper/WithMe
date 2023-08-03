package com.bonobono.backend.chatting.controller;

import com.bonobono.backend.chatting.domain.ChatMessage;
import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.chatting.dto.ChatMessageResponseDto;
import com.bonobono.backend.chatting.dto.ChatRoomRequestDto;
import com.bonobono.backend.chatting.dto.ChatRoomResponseDto;
import com.bonobono.backend.chatting.dto.ChatRoomWithMessagesDto;
import com.bonobono.backend.chatting.mongo.ChatMessageRepository;
import com.bonobono.backend.chatting.repository.ChatRoomRepository;
import com.bonobono.backend.chatting.service.ChatMessageService;
import com.bonobono.backend.chatting.service.ChatRoomService;
import com.bonobono.backend.member.entity.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.BorderUIResource;
import java.util.ArrayList;
import java.util.HashMap;
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

    //방이 이미 있으면 get아니면 새로 save하기
//    @GetMapping("/{roomNumber}/{other}")
//    public ChatRoomWithMessagesDto makeRoom(@PathVariable String roomNumber, @PathVariable String other, @AuthenticationPrincipal Member member) {
//        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findByRoomNumber(roomNumber);
//        if (optionalChatRoom.isPresent()) {
//            List<ChatMessageResponseDto> chatMessage = chatMessageService.findByRoomNumber(roomNumber);
//            ChatRoom chatRoom = optionalChatRoom.get();
//            return new ChatRoomWithMessagesDto(chatRoom,chatMessage);
//        }
//        else {
//            ChatRoomRequestDto requestDto = new ChatRoomRequestDto(other, roomNumber, member);
//            ChatRoom newChatRoom=chatRoomService.save(requestDto);
//            return new ChatRoomWithMessagesDto(newChatRoom, new ArrayList<>());
//        }
//    }

    @GetMapping("/{roomNumber}/{other}")
    public ChatRoomWithMessagesDto makeRoom(@PathVariable String roomNumber, @PathVariable String other, @RequestParam("member") Long memberId) {
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findByRoomNumber(roomNumber);
        if (optionalChatRoom.isPresent()) {
            List<ChatMessageResponseDto> chatMessage = chatMessageService.findByRoomNumber(roomNumber);
            ChatRoom chatRoom = optionalChatRoom.get();
            return new ChatRoomWithMessagesDto(chatRoom,chatMessage);
        } else {
            Optional<Member> optionalMember = memberRepository.findById(memberId);
            if (optionalMember.isPresent()) {
                ChatRoomRequestDto requestDto = new ChatRoomRequestDto(other, roomNumber, optionalMember.get());
                ChatRoom newChatRoom=chatRoomService.save(requestDto);
                return new ChatRoomWithMessagesDto(newChatRoom, new ArrayList<>());
            } else {
                throw new IllegalArgumentException("invalid memberId: " + memberId);
            }
        }
    }


    //전체 조회
    @GetMapping("/list")
    public List<ChatRoomResponseDto> findAll() {
        return chatRoomService.findByList();
    }

    //채팅방 일부 검색으로 조회
    @GetMapping("/search")
    public List<ChatRoomResponseDto> findByOther(@RequestParam("other") String other) {
        return chatRoomService.findByOther(other);
    }

    //채팅방 삭제
    //채팅방 삭제하면 채팅도 삭제되는지 확인 필요
    @DeleteMapping("/{roomNumber}")
    public void DeleteChat(@PathVariable String roomNumber) {
        chatRoomService.delete(roomNumber);

    }

}
