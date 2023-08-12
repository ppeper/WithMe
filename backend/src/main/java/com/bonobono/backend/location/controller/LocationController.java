package com.bonobono.backend.location.controller;

import com.bonobono.backend.global.util.SecurityUtil;
import com.bonobono.backend.location.dto.req.LocationSaveRequestDto;
import com.bonobono.backend.location.dto.res.LocationListResponseDto;
import com.bonobono.backend.location.dto.res.LocationSelectResponseDto;
import com.bonobono.backend.location.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "location", description = "장소(해변)")
@RequiredArgsConstructor
@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    @Operation(summary = "장소 정보 전체 조회")
    @GetMapping("")
    public ResponseEntity<List<LocationListResponseDto>> findAllDesc(){
        List<LocationListResponseDto> responseDto =  locationService.findAll();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "장소 선택용 조회", description = "신고게시판에서 장소 선택할 때 사용")
    @GetMapping("/select")
    public ResponseEntity<List<LocationSelectResponseDto>> findAllForSelect(){
        List<LocationSelectResponseDto> responseDtos = locationService.findAllForSelect();
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @Operation(summary = "장소 정보 저장")
    @PostMapping("")
    public ResponseEntity<Void> save(@RequestBody LocationSaveRequestDto requestDto){
        locationService.save(SecurityUtil.getLoginMemberId(), requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
