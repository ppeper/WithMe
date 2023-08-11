package com.bonobono.backend.location.service;

import com.bonobono.backend.location.dto.req.CampaignSaveRequestDto;
import com.bonobono.backend.location.dto.res.CampaignListResponseDto;
import com.bonobono.backend.location.entity.Location;
import com.bonobono.backend.location.repository.CampaignRepository;
import com.bonobono.backend.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final LocationRepository locationRepository;

    // 캠페인 전체 조회 등록 최신순
    @Transactional(readOnly = true)
    public List<CampaignListResponseDto> findAllDesc() {
        return campaignRepository.findAllByOrderByIdDesc().stream()
                .map(CampaignListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 캠페인 장소별 조회 최신순
    @Transactional(readOnly = true)
    public List<CampaignListResponseDto> findAllByLocation(Long locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 장소가 없습니다. id =" + locationId));

        return campaignRepository.findAllByLocationOrderByIdDesc(location).stream()
                .map(CampaignListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 캠페인 등록
    @Transactional
    public void save(CampaignSaveRequestDto requestDto){
        Location location = locationRepository.findById(requestDto.getLocationId())
                .orElseThrow(() -> new IllegalArgumentException("해당 장소가 없습니다. id =" + requestDto.getLocationId()));
        campaignRepository.save(requestDto.toEntity(location));
    }
}
