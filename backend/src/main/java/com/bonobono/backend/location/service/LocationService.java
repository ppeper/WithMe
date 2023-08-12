package com.bonobono.backend.location.service;

import com.bonobono.backend.global.exception.UserNotAuthorizedException;
import com.bonobono.backend.location.dto.req.LocationSaveRequestDto;
import com.bonobono.backend.location.dto.res.LocationListResponseDto;
import com.bonobono.backend.location.dto.res.LocationSelectResponseDto;
import com.bonobono.backend.location.repository.LocationRepository;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.domain.enumtype.Role;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final MemberRepository memberRepository;

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

    // 장소 저장하기 (관리자)
    @Transactional
    public void save(Long memberId, LocationSaveRequestDto requestDto){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다. id =" + memberId));

        if(member.getRole().stream().anyMatch(authority -> authority.getRole().equals(Role.ADMIN.toString()))) {
            locationRepository.save(requestDto.toEntity());
        } else {
            throw new UserNotAuthorizedException("해당 멤버는 관리자가 아닙니다.");
        }

    }

}
