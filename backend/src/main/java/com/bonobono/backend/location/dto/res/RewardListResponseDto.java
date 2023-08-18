package com.bonobono.backend.location.dto.res;

import com.bonobono.backend.location.entity.Reward;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RewardListResponseDto {

    private int count;
    private String nickname;
    private String profileImg;

    public RewardListResponseDto(Reward entity){
        this.count = entity.getCount();
        this.nickname = entity.getMember().getNickname();
        this.profileImg = entity.getMember().getProfileImg().getImageUrl();
    }
}
