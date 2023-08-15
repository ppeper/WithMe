package com.bonobono.backend.member.domain;

import com.bonobono.backend.character.domain.UserCharacter;
import com.bonobono.backend.chatting.domain.ChatRoom;
import com.bonobono.backend.global.entity.BaseTimeEntity;
import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleComment;
import com.bonobono.backend.location.entity.Reward;
import com.bonobono.backend.member.domain.enumtype.Provider;
import com.bonobono.backend.member.dto.request.MemberUpdateRequestDto;
import com.bonobono.backend.member.dto.request.PasswordChangeRequestDto;
import com.bonobono.backend.member.dto.request.ProfileImgRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProfileImg profileImg;

    @ManyToMany
    @JoinTable(
            name = "role",
            joinColumns = {@JoinColumn(name="member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "role", referencedColumnName = "role")})
    private Set<Authority> role = new HashSet<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<ArticleComment> articleComments = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<Article> articleLikes;

    //주인은 외래키를 가지는 chatting, map을 당하는 member
    @JsonIgnore
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ChatRoom> chatRooms = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "member")
    private List<UserCharacter> userCharacters = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Reward> rewards = new ArrayList<>();

    public UserCharacter getMainCharacter() {
        for (UserCharacter character : this.userCharacters) {
            if (character.isMain()) {
                return character;
            }
        }
        return null;
    }

    @Builder
    public Member(String username, String password, String name, String nickname, String phoneNumber, Provider provider, ProfileImg profileImg, Set<Authority> role) {

        this.username = username;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.provider = provider;
        this.profileImg = profileImg;
        this.role = role;
    }

    // 회원 정보 수정
    public void updateMember(MemberUpdateRequestDto dto) {

        if(dto.getName() != null) this.name = dto.getName();
        if(dto.getNickname() != null) this.nickname = dto.getNickname();
        if(dto.getPhoneNumber() != null) this.phoneNumber = dto.getPhoneNumber();
    }

    // 비밀번호 수정
    public void changePassword(PasswordChangeRequestDto dto, BCryptPasswordEncoder passwordEncoder) {

        if((passwordEncoder.matches(dto.getPassword(), this.password)) && dto.getNewPassword() != null)
            this.password = passwordEncoder.encode(dto.getNewPassword());
    }
}