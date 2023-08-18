package com.bonobono.backend.location.dto.req;

import com.bonobono.backend.location.entity.Location;
import com.bonobono.backend.location.entity.Reward;
import com.bonobono.backend.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RewardRequestDto {

    private Long memberId;
    private Long locationId;


    @Builder
    public RewardRequestDto(Long memberId, Long locationId) {
        this.memberId = memberId;
        this.locationId = locationId;
    }

    public Reward toEntity(Member member, Location location) {
        return Reward.builder()
                .member(member)
                .location(location)
                .build();
    }
}