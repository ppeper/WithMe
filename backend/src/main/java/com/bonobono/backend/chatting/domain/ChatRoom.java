package com.bonobono.backend.chatting.domain;

import com.bonobono.backend.global.entity.BaseTimeEntity;
import com.bonobono.backend.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name="chatroom")
public class ChatRoom extends BaseTimeEntity { //생성시각 상속
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member; //방을 만든 사람

    private String other; //대화상대

    private String roomNumber; //메시지의 room번호와 연결위해(클라이언트에게 받아야하나..)

    @Builder
    public ChatRoom(String other, String roomNumber) {
        this.other=other;
        this.roomNumber=roomNumber;
    }

}
