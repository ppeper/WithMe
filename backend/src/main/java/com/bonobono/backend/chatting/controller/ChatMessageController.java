package com.bonobono.backend.chatting.controller;

import com.bonobono.backend.chatting.domain.ChatMessage;
import com.bonobono.backend.chatting.dto.ChatMessageRequestDto;
import com.bonobono.backend.chatting.dto.ChatMessageResponseDto;
import com.bonobono.backend.chatting.dto.ChatRoomResponseDto;
import com.bonobono.backend.chatting.service.ChatMessageService;
import com.bonobono.backend.chatting.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;


    //채팅방 조회 후 메시지 쓰기
    @GetMapping("/chatting/{roomId}")
    public void findById(@PathVariable Long roomId) {
        chatMessageService.save(roomId);
    }

    //채팅방 나가면, 서버 끊기게



    //
}
