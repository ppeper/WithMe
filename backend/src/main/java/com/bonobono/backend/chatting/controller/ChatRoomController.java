package com.bonobono.backend.chatting.controller;

import com.bonobono.backend.chatting.dto.ChatRoomRequestDto;
import com.bonobono.backend.chatting.dto.ChatRoomResponseDto;
import com.bonobono.backend.chatting.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    //채팅방 만들기
    @PostMapping("/chatting")
    public Long save(@RequestParam HashMap<Object, Object> params) {
        String roomName = (String) params.get("roomName");
        ChatRoomRequestDto requestDto = new ChatRoomRequestDto();
        if(roomName != null && !roomName.trim().equals("")) {
            requestDto = new ChatRoomRequestDto(roomName);
        }
        return chatRoomService.save(requestDto);
    }

    //전체 조회
    @GetMapping("/chatting")
    public List<ChatRoomResponseDto> findAll() {
        return chatRoomService.findByList();
    }

    //채팅방 일부 검색으로 조회
    @GetMapping("/chatting/search")
    public List<ChatRoomResponseDto> findByName(@RequestParam("roomName") String roomname) {
        return chatRoomService.findByRoomName(roomname);
    }

    //채팅방 삭제
    //채팅방 삭제하면 채팅도 삭제되는지 확인 필요
    @DeleteMapping("/chatting/{roomId}")
    public void DeleteChat(@PathVariable Long roomId) {
        chatRoomService.delete(roomId);

    }

}
