package com.bonobono.backend.chatting.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Getter
@Setter
@Document(collection = "chatMessage")
//@Entity
@NoArgsConstructor
public class ChatMessage {
    @Id
    private String id;
    private String sessionId; //sessionid(
    private String userName; //username;
    private String msg; //msg
    private String file; // file(BYTE형식으로 변환?)
    private String roomNumber;




    @Builder //생성자 빌드
    public ChatMessage(String msg, String file, String roomNumber, String userName, String sessionId) {
        this.msg = msg;
        this.file = file;
        this.roomNumber=roomNumber;
        this.userName=userName;
        this.sessionId=sessionId;
    }

}