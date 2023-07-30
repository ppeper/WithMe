package com.bonobono.backend.chatting.domain;

import com.bonobono.backend.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.persistence.*;
import java.util.Date;

@Getter
@Document(collection = "ChatMessage")
@NoArgsConstructor
public class ChatMessage {
    @Id
    @Field(value = "_id", targetType = FieldType.OBJECT_ID)
    private String id;

    //연관관계를 어떻게 넣어야할까???
    @DBRef
    @Transient
    @Field("chatroom")
    private ChatRoom chatRoom; //채팅방

    @Field("sender")
    private String sender;

    @Field("message")
    private String message;

//    @Field("img_url")
//    private String imgUrl;

    @CreatedDate
    @Field("createTime")
    private Date createdTime;

    @LastModifiedDate
    @Field("updateTime")
    private Date updatedTime;

    @Builder //생성자 빌드
    public ChatMessage(String sender, String message, ChatRoom chatRoom) {
        this.sender = sender;
        this.message = message;
        this.chatRoom = chatRoom;
//        this.imgUrl=imgUrl;
    }

}