package com.bonobono.backend.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nickname;
    private String accountId;
    private String password;
    private String phoneNumber;
    private String profileImg;

//    @Builder
//    public Member(String name, String nickname, String accountId, String password, String phoneNumber, String profileImg) {
//        this.name = name;
//        this.nickname = nickname;
//        this.accountId = accountId;
//        this.password = password;
//        this.phoneNumber = phoneNumber;
//        this.profileImg = profileImg;
//    }

}
