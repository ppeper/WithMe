package com.bonobono.backend.member.domain;

import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.global.entity.BaseTimeEntity;
import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleComment;
import com.bonobono.backend.member.domain.enumtype.Provider;
import com.bonobono.backend.member.domain.enumtype.Role;
import com.bonobono.backend.member.dto.request.MemberUpdateRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Ref;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    private Provider provider;

    @ManyToMany
    @JoinTable(
        name = "role",
        joinColumns = {@JoinColumn(name="member_id", referencedColumnName = "member_id")},
        inverseJoinColumns = {@JoinColumn(name = "role", referencedColumnName = "role")})
    private Set<Authority> role = new HashSet<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<ArticleComment> articleComments = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private Set<Article> articleLikes;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL) //주인은 외래키를 가지는 order, map을 당하는 member
    private List<ChatRoom> chatRooms = new ArrayList<>();

    @Builder
    public Member(String username, String password, String name, String nickname, String phoneNumber, Provider provider, Set<Authority> role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.provider = provider;
        this.role = role;
    }

    // 회원 정보 수정
    public void updateMember(MemberUpdateRequestDto dto, PasswordEncoder passwordEncoder) {
        if(dto.getPassword() != null) this.password = passwordEncoder.encode(dto.getPassword());
        if(dto.getName() != null) this.name = dto.getName();
        if(dto.getNickname() != null) this.nickname = dto.getNickname();
        if(dto.getPhoneNumber() != null) this.phoneNumber = dto.getPhoneNumber();
    }

}
