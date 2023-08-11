package com.bonobono.backend.location.service;

import com.bonobono.backend.location.dto.req.LocationSaveRequestDto;
import com.bonobono.backend.location.dto.res.LocationListResponseDto;
import com.bonobono.backend.location.dto.res.LocationSelectResponseDto;
import com.bonobono.backend.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final LocationRepository locationRepository;


    // 모든 장소 불러오기
    @Transactional(readOnly = true)
    public List<LocationListResponseDto> findAll() {
        return locationRepository.findAll().stream()
                .map(LocationListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 장소 선택용 불러오기
    @Transactional(readOnly = true)
    public List<LocationSelectResponseDto> findAllForSelect(){
        return locationRepository.findAll().stream()
                .map(LocationSelectResponseDto::new)
                .collect(Collectors.toList());
    }

    // 장소 저장하기
    @Transactional
    public void save(LocationSaveRequestDto requestDto){
        locationRepository.save(requestDto.toEntity());
    }

}
