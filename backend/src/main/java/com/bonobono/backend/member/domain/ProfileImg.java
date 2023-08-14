package com.bonobono.backend.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ProfileImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_img_id")
    private Long imageId;

    private String imageName;
    private String imageUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

//    @Builder
//    public ProfileImg(Long imageId, String imageName, String imageUrl, Member member) {
//        this.imageId = imageId;
//        this.imageName = imageName;
//        this.imageUrl = imageUrl;
//        this.member = member;
//
//    }
//
//    @Builder(builderMethodName = "createProfileImg")
//    public ProfileImg(String imgUrl, String imgName, Member member) {
//        this.imageUrl = imgUrl;
//        this.imageName = imgName;
//        this.member = member;
//        member.setProfileImg(this);
//    }
}
