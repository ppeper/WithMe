package com.bonobono.backend.location.controller;

import com.bonobono.backend.location.dto.req.CampaignSaveRequestDto;
import com.bonobono.backend.location.dto.res.CampaignListResponseDto;
import com.bonobono.backend.location.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/campaign")
public class CampaignController {

    private final CampaignService campaignService;

    // 캠페인 전체 정보 조회
    @GetMapping("")
    public ResponseEntity<List<CampaignListResponseDto>> findAllDesc(){
        List<CampaignListResponseDto> responseDto =  campaignService.findAllDesc();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 캠페인 정보 저장
    @PostMapping("")
    public ResponseEntity<Void> save(@RequestBody CampaignSaveRequestDto requestDto){
        campaignService.save(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
