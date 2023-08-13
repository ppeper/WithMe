package com.bonobono.backend.chatting.service;

import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.chatting.dto.ChatRoomRequestDto;
import com.bonobono.backend.chatting.dto.ChatRoomResponseDto;
import com.bonobono.backend.chatting.repository.ChatRoomRepository;
import com.bonobono.backend.global.util.SecurityUtil;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;// 채팅방조회
    private final MemberRepository memberRepository;


//    //채팅방 조회(이름으로 검색)
//    @Transactional
//    public List<ChatRoomResponseDto> findByOther(String other) {
//        Sort sort = Sort.by(Sort.Direction.ASC, "id");
//        List<ChatRoom> chatRoomList = this.chatRoomRepository.findByOther(other);
//        return chatRoomList.stream().map(ChatRoomResponseDto::new).collect(Collectors.toList());
//    }

    //전체 채팅방 목록 조회
    @Transactional(readOnly = true)
    public List<ChatRoomResponseDto> findByList() {
        return chatRoomRepository.findAll(Sort.by("id")).stream()
                .map(ChatRoomResponseDto::new)
                .collect(Collectors.toList());
    }

    //채팅방 개설
    @Transactional
    public ChatRoom save(final ChatRoomRequestDto requestDto) {
        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
        Member other = memberRepository.findByNickname(requestDto.getNickname())
                .orElseThrow(() -> new RuntimeException("채팅 상대방 정보가 없습니다"));
        return this.chatRoomRepository.save(requestDto.toEntity(member, other));
    }

    //채팅방 삭제
    @Transactional
    public void delete(final int roomNumber) {
        ChatRoom chatRoom = this.chatRoomRepository.findByRoomNumber(roomNumber).orElseThrow(
                ()->new IllegalArgumentException("해당 chatroom이 존재하지 않습니다 +id"+roomNumber));
        this.chatRoomRepository.delete(chatRoom);
    }


}
