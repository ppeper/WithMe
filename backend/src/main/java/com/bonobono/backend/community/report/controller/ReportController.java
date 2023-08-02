package com.bonobono.backend.community.report.controller;

import com.bonobono.backend.community.report.dto.ReportRequestdto;
import com.bonobono.backend.community.report.dto.ReportResponseDto;
import com.bonobono.backend.community.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/community/report")
public class ReportController {

    private final ReportService reportService;

    // 신고 전체 글 조회
    @GetMapping("/report")
    public List<ReportResponseDto> findAllDesc(){
        return reportService.findAllDesc();
    }

    // 신고 게시글 작성
    @PostMapping
    public Long save(@RequestBody ReportRequestdto requestdto){
        return reportService.save(requestdto);
    }

    // 신고 특정 게시글 조회

    // 신고 특정 게시글 삭제

    // 신고 특정 게시글 수정
    
    // 신고 게시글 검색

    // 신고 게시판 특정 글 좋아요

}
