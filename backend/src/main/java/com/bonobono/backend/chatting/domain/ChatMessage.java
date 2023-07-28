package com.bonobono.backend.chatting.domain;

import com.bonobono.backend.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Document(collection = "ChatMessage")
@NoArgsConstructor
public class ChatMessage extends BaseTimeEntity {
    @Id
    @Field(value = "_id", targetType = FieldType.OBJECT_ID)
    private String id;

    //연관관계를 어떻게 넣어야할까???
    @DBRef
    private ChatRoom chatRoom; //채팅방

    @Field("sender")
    private String sender;

    @Field("message")
    private String message;

    @Field("img_url")
    private String imgUrl;

    @Builder //생성자 빌드
    public ChatMessage(String sender, String message, ChatRoom chatRoom, String imgUrl) {
        this.sender = sender;
        this.message = message;
        this.chatRoom = chatRoom;
        this.imgUrl=imgUrl;
    }

}