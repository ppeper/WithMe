package com.bonobono.backend.member.dto.response;

import com.bonobono.backend.member.dto.request.MemberRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Setter
@Data
@Builder
@AllArgsConstructor
@Schema(description = "경험치까지 조회할 수 있는 회원정보 응답 Dto 입니다.")
public class MemberProfileResponseDto {

    private final MemberRequestDto member;
    private final int experience;

    public MemberProfileResponseDto(int experience, MemberRequestDto member) {

        this.member = member;
        this.experience = experience;
    }
}
