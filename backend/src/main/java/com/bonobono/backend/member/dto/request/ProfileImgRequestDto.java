package com.bonobono.backend.member.dto.request;

import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.ProfileImg;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "프로필 사진 Dto 입니다.")
public class ProfileImgRequestDto {

    @Schema(name = "이미지 이름")
    private String imgName;

    @Schema(name = "이미지 url")
    private String imgUrl;

    @Builder
    public ProfileImgRequestDto(String imgName, String imgUrl) {

        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }

    public ProfileImg toEntity(Member member) {

        return ProfileImg.builder()
                .imgName(imgName)
                .imgUrl(imgUrl)
                .build();
    }

    public static ProfileImgRequestDto toRequestDto(ProfileImg img) {

        ProfileImgRequestDto request = new ProfileImgRequestDto();
        request.setImgName(img.getImageName());
        request.setImgUrl(img.getImageUrl());

        return request;
    }
}