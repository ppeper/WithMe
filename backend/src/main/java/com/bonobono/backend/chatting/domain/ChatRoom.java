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
    private int roomNumber;

    @ManyToOne
    @JoinColumn(name="my_id")
    private Member member; //방을 만든 사람

    @ManyToOne
    @JoinColumn(name="other_id")
    private Member other; //대화상대

    @Builder
    public ChatRoom(Member other,  Member member) {
        this.other=other;
        this.member=member;
    }

}
