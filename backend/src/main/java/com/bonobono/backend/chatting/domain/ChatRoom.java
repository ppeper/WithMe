package com.bonobono.backend.chatting.domain;

import com.bonobono.backend.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class ChatRoom extends BaseTimeEntity { //생성시각 상속
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sender;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE, orphanRemoval = true) //채팅방 삭제-> 메시지도 삭제되도록
    private List<ChatMessage> chatMessageList;
    //ChatMessage엔티티 클래스에서 선언된 chatRoom필드에 매핑

    @Builder
    public ChatRoom(Long sender) {
        this.sender=sender;
    }

}
