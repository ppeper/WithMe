package com.bonobono.backend.location.controller;

import com.bonobono.backend.location.dto.req.LocationSaveRequestDto;
import com.bonobono.backend.location.dto.res.LocationListResponseDto;
import com.bonobono.backend.location.dto.res.LocationSelectResponseDto;
import com.bonobono.backend.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    // 장소 정보 전체 조회
    @GetMapping("")
    public ResponseEntity<List<LocationListResponseDto>> findAllDesc(){
        List<LocationListResponseDto> responseDto =  locationService.findAll();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    
    // 장소 선택용 조회
    @GetMapping("/select")
    public ResponseEntity<List<LocationSelectResponseDto>> findAllForSelect(){
        List<LocationSelectResponseDto> responseDtos = locationService.findAllForSelect();
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    // 장소 정보 저장
    @PostMapping("")
    public ResponseEntity<Void> save(@RequestBody LocationSaveRequestDto requestDto){
        locationService.save(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
