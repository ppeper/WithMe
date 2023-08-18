package com.bonobono.backend.member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Token {

    @Id
    @Column(name = "rt_key")
    private String key;

    @Column(name = "rt_value")
    private String value;

    @Column(name = "fcm_token")
    private String fcmtoken;

    @Builder
    public Token(String key, String value, String fcmtoken) {

        this.key = key;
        this.value = value;
        this.fcmtoken=fcmtoken;
    }

    public Token updateValue(String token) {

        this.value = token;
        return this;
    }
}