package com.bonobono.backend.chatting.controller;

import com.bonobono.backend.chatting.dto.ChatRoomRequestDto;
import com.bonobono.backend.chatting.dto.ChatRoomResponseDto;
import com.bonobono.backend.chatting.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    //채팅방 만들기
    @PostMapping("/chatting")
    public Long save(@RequestBody ChatRoomRequestDto requestDto) {
        return chatRoomService.save(requestDto);
    }

    //채팅방 조회(채팅을 받는 사람id로 조회)
    @GetMapping("/chatting/{sender}")
    public ChatRoomResponseDto getchat(@PathVariable Long sender) {

        return chatRoomService.findById(sender);
    }

    //채팅방 나가면, 서버 끊기게


    //채팅방 삭제
    //채팅방 삭제하면 채팅도 삭제되는지 확인 필요
    @DeleteMapping("/chatting/{sender}")
    public void deletechat(@PathVariable Long sender) {
        chatRoomService.delete(sender);
    }

}
