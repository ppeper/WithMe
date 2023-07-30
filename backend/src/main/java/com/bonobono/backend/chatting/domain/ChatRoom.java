package com.bonobono.backend.chatting.domain;

import com.bonobono.backend.BaseTimeEntity;
import com.bonobono.backend.user.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name="chatroom")
public class ChatRoom extends BaseTimeEntity { //생성시각 상속
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member; //방을 만든 사람

    private String roomName;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true) //채팅방 삭제-> 메시지도 삭제되도록
    private List<ChatMessage> chatMessageList= new ArrayList<>();
    //ChatMessage엔티티 클래스에서 선언된 chatRoom필드에 매핑

    @Builder
    public ChatRoom(String roomName) {
        this.roomName=roomName;
    }

}
