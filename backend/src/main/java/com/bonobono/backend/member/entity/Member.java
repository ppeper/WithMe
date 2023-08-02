package com.bonobono.backend.member.entity;

import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.global.entity.BaseTimeEntity;
import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleComment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String name;

    private String password;

    private String nickname;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<ArticleComment> articleComments = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private Set<Article> articleLikes;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL) //주인은 외래키를 가지는 order, map을 당하는 member
    private List<ChatRoom> chatRooms = new ArrayList<>();

}
