package com.bonobono.backend.member.dto.response;

import com.bonobono.backend.member.dto.request.ProfileImgRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Data
@Builder
@NoArgsConstructor
@Schema(description = "프로필 업로드 응답 Dto 입니다.")
public class ProfileImgResponseDto {

    private Long memberId;

    private ProfileImgRequestDto img;

    public ProfileImgResponseDto(Long memberId, ProfileImgRequestDto img) {
        this.memberId = memberId;
        this.img = img;
    }
}