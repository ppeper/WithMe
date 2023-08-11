package com.bonobono.backend.location.controller;

import com.bonobono.backend.location.dto.req.CampaignSaveRequestDto;
import com.bonobono.backend.location.dto.res.CampaignListResponseDto;
import com.bonobono.backend.location.service.CampaignService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "campaign", description = "캠페인")
@RequiredArgsConstructor
@RestController
@RequestMapping("/campaign")
public class CampaignController {

    private final CampaignService campaignService;

    @Operation(summary = "캠페인 전체 정보 조회")
    @GetMapping("")
    public ResponseEntity<List<CampaignListResponseDto>> findAllDesc(){
        List<CampaignListResponseDto> responseDto =  campaignService.findAllDesc();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "장소별 캠페인 정보 조회")
    @GetMapping("/{locationId}")
    public ResponseEntity<List<CampaignListResponseDto>> findAllByLocation(@PathVariable Long locationId){
        List<CampaignListResponseDto> responseDto =  campaignService.findAllByLocation(locationId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "캠페인 정보 저장")
    @PostMapping("")
    public ResponseEntity<Void> save(@RequestBody CampaignSaveRequestDto requestDto){
        campaignService.save(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
