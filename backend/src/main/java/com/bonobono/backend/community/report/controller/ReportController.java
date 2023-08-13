package com.bonobono.backend.community.report.controller;

import com.bonobono.backend.community.report.dto.req.ReportCommentRequestDto;
import com.bonobono.backend.community.report.dto.req.ReportSaveRequestDto;
import com.bonobono.backend.community.report.dto.req.ReportUpdateRequestDto;
import com.bonobono.backend.community.report.dto.res.ReportCommentResponseDto;
import com.bonobono.backend.community.report.dto.res.ReportDetailResponseDto;
import com.bonobono.backend.community.report.dto.res.ReportListResponseDto;
import com.bonobono.backend.community.report.service.ReportCommentService;
import com.bonobono.backend.community.report.service.ReportLikeService;
import com.bonobono.backend.community.report.service.ReportService;
import com.bonobono.backend.global.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "report", description = "신고게시판")
@RequiredArgsConstructor
@RestController
@RequestMapping("/community/report")
public class ReportController {

    private final ReportService reportService;

    private final ReportCommentService reportCommentService;

    private final ReportLikeService reportLikeService;

    @Operation(summary = "신고게시판 글쓰기")
    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@RequestPart ReportSaveRequestDto requestDto,
                                  @RequestPart(value = "imageFiles", required = false) List<MultipartFile> imageFiles){
        reportService.save(SecurityUtil.getLoginMemberId(), requestDto, imageFiles);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "신고게시판 전체 글 조회")
    @GetMapping("")
    public ResponseEntity<List<ReportListResponseDto>> findAllDesc(){
        List<ReportListResponseDto> responseDto =  reportService.findAllDesc();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "신고게시판 특정 글, 글에 관한 댓글 조회하기")
    @GetMapping("/{reportId}")
    public ResponseEntity<ReportDetailResponseDto> findById(@PathVariable Long reportId) {
        ReportDetailResponseDto responseDto = reportService.findById(SecurityUtil.getLoginMemberId(), reportId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "신고게시판 게시글 검색", description = "키워드가 제목 내용 포함")
    @GetMapping("/search")
    public ResponseEntity<List<ReportListResponseDto>> search(@RequestParam("keyword") String keyword){
        List<ReportListResponseDto> responseDto = reportService.search(keyword);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @Operation(summary = "신고게시판 특정 글 수정")
    @PatchMapping(value = "/{reportId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long reportId,
                                       @RequestPart ReportUpdateRequestDto requestDto,
                                       @RequestPart(value = "imageFiles", required = false) List<MultipartFile> imageFiles){
        reportService.update(SecurityUtil.getLoginMemberId(), reportId , requestDto, imageFiles);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "신고게시판 관리자 확인 처리")
    @PatchMapping("/{reportId}/admin-complete")
    public ResponseEntity<Void> update(@PathVariable Long reportId){
        reportService.updateRecruitStatus(SecurityUtil.getLoginMemberId(), reportId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "신고게시판 특정 글 삭제")
    @DeleteMapping("/{reportId}")
    public ResponseEntity<Void> delete(@PathVariable Long reportId){
        reportService.delete(SecurityUtil.getLoginMemberId(), reportId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // ----------댓글---------
    @Operation(summary = "신고게시판 글에 댓글 쓰기", description = "관리자 또는 작성자만 작성 가능")
    @PostMapping("/{reportId}/comment")
    public ResponseEntity<ReportCommentResponseDto> saveComment(@PathVariable Long reportId,
                                                                @RequestBody ReportCommentRequestDto requestDto
    ){
        ReportCommentResponseDto responseDto = reportCommentService.save(SecurityUtil.getLoginMemberId(), reportId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "신고게시판 댓글 삭제")
    @DeleteMapping("/{reportId}/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long reportId,
                                              @PathVariable Long commentId){
        reportCommentService.delete(SecurityUtil.getLoginMemberId(), reportId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "신고게시판 댓글 수정")
    @PatchMapping("/{reportId}/comment/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable Long reportId,
                                              @PathVariable Long commentId,
                                              @RequestBody ReportCommentRequestDto requestDto){
        reportCommentService.update(SecurityUtil.getLoginMemberId(), reportId, commentId, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "신고게시판 좋아요")
    @PatchMapping("/{reportId}/like")
    public ResponseEntity<Void> like(@PathVariable Long reportId) {
        reportLikeService.like(SecurityUtil.getLoginMemberId(), reportId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
