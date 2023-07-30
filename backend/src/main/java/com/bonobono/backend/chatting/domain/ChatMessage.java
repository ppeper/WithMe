package com.bonobono.backend.chatting.domain;

import com.bonobono.backend.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class ChatMessage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sender; //메시지 발신자(user)
    private String message;
//  private String uploadFileName; // 작성자가 업로드한 파일명(S3로 파일 저장)
//  private String storeFileName; //서버 내부서 관리하는 파일명
//    private Boolean isCheck; //상대방 확인여부는 DB가 아니라, 스레드를 통해서 확인가능?

    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom chatRoom; //채팅방


    @Builder //생성자 빌드
    public ChatMessage(String sender, String message, ChatRoom chatRoom) {
        this.sender = sender;
        this.message = message;
        this.chatRoom = chatRoom;
//        this.imgUrl=imgUrl;
    }

}