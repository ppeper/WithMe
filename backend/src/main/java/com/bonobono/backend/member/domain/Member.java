package com.bonobono.backend.member.domain;

import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.global.entity.BaseTimeEntity;
import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleComment;
import com.bonobono.backend.location.entity.Reward;
import com.bonobono.backend.member.domain.enumtype.Provider;
import com.bonobono.backend.member.domain.enumtype.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.*;
import lombok.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member extends BaseTimeEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String password;

    private String name;

    private String nickname;

    private String phoneNumber;

    private String profileImg;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    private String refreshToken;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<ArticleComment> articleComments = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<Article> articleLikes;

    @JsonIgnore
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL) //주인은 외래키를 가지는 chatting, map을 당하는 member
    private List<ChatRoom> chatRooms = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "member")
    private List<UserCharacter> userCharacters = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Reward> rewards = new ArrayList<>();

    public UserCharacter getMainCharacter() {
        for (UserCharacter character : this.userCharacters) {
            if (character.getMain()) {
                return character;
            }
        }
        return null;
    }
}