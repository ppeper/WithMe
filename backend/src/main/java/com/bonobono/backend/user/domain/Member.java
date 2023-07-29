package com.bonobono.backend.user.domain;

import com.bonobono.backend.chatting.domain.ChatRoom;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name="member_id") //pk컬럼명
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "member") //주인은 외래키를 가지는 order, map을 당하는 member
    private List<ChatRoom> chatRooms = new ArrayList<>();

}
