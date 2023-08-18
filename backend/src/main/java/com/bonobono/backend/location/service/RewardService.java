package com.bonobono.backend.location.service;

import com.bonobono.backend.location.dto.req.RewardRequestDto;
import com.bonobono.backend.location.dto.res.RewardListResponseDto;
import com.bonobono.backend.location.entity.Location;
import com.bonobono.backend.location.entity.Reward;
import com.bonobono.backend.location.repository.LocationRepository;
import com.bonobono.backend.location.repository.RewardRepository;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RewardService {

    private final RewardRepository rewardRepository;

    private final LocationRepository locationRepository;

    private final MemberRepository memberRepository;

    // 장소별 리워드 랭킹
    public List<RewardListResponseDto> findAllByLocation(Long locationId){
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 장소가 없습니다. id =" + locationId));
        return rewardRepository.findAllByLocation(location).stream()
                .map(RewardListResponseDto::new)
                .collect(Collectors.toList());

    }

    // 리워드 횟수 증가 없으면 등록
    public void updateRewardCnt(RewardRequestDto requestDto){
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다. id =" + requestDto.getMemberId()));

        Location location = locationRepository.findById(requestDto.getLocationId())
                .orElseThrow(() -> new IllegalArgumentException("해당 장소가 없습니다. id =" + requestDto.getLocationId()));

        Optional<Reward> reward  = rewardRepository.findByMemberAndLocation(member, location);

        if(reward.isPresent()){
            reward.get().updateRewardCnt();
        } else {
            rewardRepository.save(requestDto.toEntity(member, location));
        }
    }
}
