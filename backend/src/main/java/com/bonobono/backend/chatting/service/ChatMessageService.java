package com.bonobono.backend.chatting.service;

import com.bonobono.backend.chatting.config.MyServer;
import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.chatting.repository.ChatMessageRepository;
import com.bonobono.backend.chatting.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ChatMessageService {
    //메시지 저장
    private final ChatMessageRepository chatMessageRepoitory;
    private final ChatRoomRepository chatRoomRepository;


    public void save(Long id) {
        try {
            MyServer chatServer = new MyServer();
            chatServer.start(id); //치팅방정보는 api로 전달
        } catch (IOException e) {
            System.out.println("[서버] " + e.getMessage());
        }
    }


//        return this.chatMessageRepository.save(requestDto.toEntity()).getId();

//        try {
//            MyServer chatServer = new MyServer();
//            chatServer.start();
//
//            Scanner scanner = new Scanner(System.in); //input값
//            while(true) {
//                String key = scanner.nextLine();
//                if(key.equals("q")) 	break;
//            }
//            scanner.close();
//            chatServer.stop();
//        } catch(IOException e) {
//            System.out.println("[서버] " + e.getMessage());
//        }
//        return this.chatMessageRepository.save(requestDto.toEntity()).getId();
    }

