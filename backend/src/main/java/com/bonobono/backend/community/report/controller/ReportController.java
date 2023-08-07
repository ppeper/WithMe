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
import com.bonobono.backend.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/community/report")
public class ReportController {

    private final ReportService reportService;

    private final ReportCommentService reportCommentService;

    private final ReportLikeService reportLikeService;

    // 신고게시판 글쓰기

    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@AuthenticationPrincipal Member member,
                                  @RequestPart ReportSaveRequestDto requestDto,
                                  @RequestPart(value = "imageFiles", required = false) List<MultipartFile> imageFiles){
        reportService.save(member, requestDto, imageFiles);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    // 신고게시판 전체 글 조회
    @GetMapping("")
    public ResponseEntity<List<ReportListResponseDto>> findAllDesc(){
        List<ReportListResponseDto> responseDto =  reportService.findAllDesc();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 신고게시판 특정 글, 글에 관한 댓글 조회하기
    @GetMapping("/{reportId}")
    public ResponseEntity<ReportDetailResponseDto> findById(@AuthenticationPrincipal Member member,
                                                            @PathVariable Long reportId) {
        ReportDetailResponseDto responseDto = reportService.findById(member, reportId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 신고게시판 게시글 검색 (키워드가 제목, 내용 포함)
    @GetMapping("/search")
    public ResponseEntity<List<ReportListResponseDto>> search(@RequestParam("keyword") String keyword){
        List<ReportListResponseDto> responseDto = reportService.search(keyword);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    // 신고게시판 특정 글 수정
    @PatchMapping(value = "/{reportId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> update(@AuthenticationPrincipal Member member,
                                       @PathVariable Long reportId,
                                       @RequestPart ReportUpdateRequestDto requestDto,
                                       @RequestPart(value = "imageFiles", required = false) List<MultipartFile> imageFiles){
        reportService.update(member, reportId , requestDto, imageFiles);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{reportId}/admin-complete")
    public ResponseEntity<Void> update(@AuthenticationPrincipal Member member,
                                       @PathVariable Long reportId){
        reportService.updateRecruitStatus(member, reportId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 신고게시판 특정 글 삭제
    @DeleteMapping("/{reportId}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal Member member,
                                       @PathVariable Long reportId){
        reportService.delete(member, reportId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // ----------댓글---------
    // 신고게시판 글에 댓글 쓰기 (관리자 또는 작성자만)
    @PostMapping("/{reportId}/comment")
    public ResponseEntity<ReportCommentResponseDto> saveComment(@AuthenticationPrincipal Member member,
                                                                @PathVariable Long reportId,
                                                                @RequestBody ReportCommentRequestDto requestDto
    ){
        ReportCommentResponseDto responseDto = reportCommentService.save(member, reportId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 신고게시판 댓글 삭제
    @DeleteMapping("/{reportId}/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@AuthenticationPrincipal Member member,
                                              @PathVariable Long reportId,
                                              @PathVariable Long commentId){
        reportCommentService.delete(member, reportId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 신고게시판 댓글 수정
    @PatchMapping("/{reportId}/comment/{commentId}")
    public ResponseEntity<Void> updateComment(@AuthenticationPrincipal Member member,
                                              @PathVariable Long reportId,
                                              @PathVariable Long commentId,
                                              @RequestBody ReportCommentRequestDto requestDto){
        reportCommentService.update(member, reportId, commentId, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 신고게시판 좋아요
    @PatchMapping("/{reportId}/like")
    public ResponseEntity<Void> like(@AuthenticationPrincipal Member member,
                                     @PathVariable Long reportId) {
        reportLikeService.like(member, reportId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
