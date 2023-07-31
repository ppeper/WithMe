package com.bonobono.backend.chatting.domain;

import com.bonobono.backend.BaseTimeEntity;
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
public class ChatMessage extends BaseTimeEntity {
    @Id
    private String id;
    private String sessionId; //sessionId
    private String sender; //메시지 발신자(userName)
    private String message; //msg
    private String imgByte; // file

//    @ManyToOne(fetch = FetchType.LAZY)
//    private ChatRoom chatRoom; //roomNumber


    @Builder //생성자 빌드
    public ChatMessage(String sender, String message, String imgByte) {
        this.sender = sender;
        this.message = message;
        this.imgByte = imgByte;
    }

}