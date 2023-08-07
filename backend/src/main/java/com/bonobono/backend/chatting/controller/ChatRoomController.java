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

    /**
     * 맴버정보도 들고오기*/
    @GetMapping("/")
    public ResponseEntity<ChatRoomWithMessagesDto> makeRoom(@RequestBody ChatRoomRequestDto chatRoomRequestDto) {
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findByRoomNumber(chatRoomRequestDto.getRoomNumber());

        //채팅방이 이미있으면, 반환
        if (optionalChatRoom.isPresent()) {
            List<ChatMessageResponseDto> chatMessage = chatMessageService.findByRoomNumber(chatRoomRequestDto.getRoomNumber());
            ChatRoom chatRoom = optionalChatRoom.get();
           ChatRoomWithMessagesDto chatRoomWithMessagesDto = new ChatRoomWithMessagesDto(chatRoom,chatMessage);
            return ResponseEntity.ok(chatRoomWithMessagesDto);
        //없으면 새로 만들기
        } else {
            Optional<Member> optionalMember = memberRepository.findById(chatRoomRequestDto.getMember().getId());
            if (optionalMember.isPresent()) {
                ChatRoomRequestDto requestDto = new ChatRoomRequestDto(chatRoomRequestDto.getOther(), chatRoomRequestDto.getRoomNumber(), optionalMember.get());
                ChatRoom newChatRoom=chatRoomService.save(requestDto);
                ChatRoomWithMessagesDto chatRoomWithMessagesDto = new ChatRoomWithMessagesDto(newChatRoom, new ArrayList<>());
                return ResponseEntity.ok(chatRoomWithMessagesDto);
            } else {
                throw new IllegalArgumentException("사용자를 찾을 수 없습니다 memberId: " + chatRoomRequestDto.getMember().getId());
            }
        }
    }


    //전체 조회
    @GetMapping("/list")
    public ResponseEntity<List<ChatRoomResponseDto>> findAll() {
        List<ChatRoomResponseDto> roomResponseDtos = chatRoomService.findByList();
        return ResponseEntity.ok(roomResponseDtos);
    }

    //채팅방 일부 검색으로 조회(쿼리 dsl 사용)
    @GetMapping("/search")
    public List<ChatRoomResponseDto> findByOther(@RequestParam("other") String other) {
        return chatRoomService.findByOther(other);
    }

    //채팅방 삭제
    //채팅방 삭제하면 채팅도 삭제되는지 확인 필요
    @DeleteMapping("/{roomNumber}")
    public ResponseEntity<Void> DeleteChat(@PathVariable String roomNumber) {
        chatRoomService.delete(roomNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
