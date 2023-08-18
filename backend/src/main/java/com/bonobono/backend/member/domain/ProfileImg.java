package com.bonobono.backend.member.domain;

import com.bonobono.backend.member.dto.request.ProfileImgRequestDto;
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

    @Builder
    public ProfileImg(String imgUrl, String imgName, Member member) {

        this.imageUrl = imgUrl;
        this.imageName = imgName;
        this.member = member;
    }

    // ProfileImgDto 객체로 변환
    public ProfileImgRequestDto toRequestDto() {

        return ProfileImgRequestDto.toRequestDto(this);
    }

    public void updateImage(ProfileImgRequestDto dto) {

        this.imageUrl = dto.getImgUrl();
        this.imageName = dto.getImgName();
    }
}