package com.bonobono.backend.member.dto.request;

import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.ProfileImg;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "프로필 사진 Dto")
public class ProfileImgRequestDto {

    @Schema(name = "이미지 번호")
    private Long imgId;
    @Schema(name = "이미지 이름")
    private String imgName;
    @Schema(name = "이미지 url")
    private String imgUrl;

    @Builder
    public ProfileImgRequestDto(Long imgId,String imgName, String imgUrl) {
        this.imgId = imgId;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }

//    public ProfileImg toEntity(Member member) {
//        return ProfileImg.builder()
//                .imageId(imgId)
//                .imageName(imgName)
//                .imageUrl(imgUrl)
//                .build();
//    }
}
