package com.bonobono.backend.community.article.controller;

import com.bonobono.backend.community.article.dto.req.ArticleCommentRequestDto;
import com.bonobono.backend.community.article.dto.req.ArticleSaveRequestDto;
import com.bonobono.backend.community.article.dto.req.ArticleUpdateRequestDto;
import com.bonobono.backend.community.article.dto.res.ArticleCommentResponseDto;
import com.bonobono.backend.community.article.dto.res.ArticleDetailResponseDto;
import com.bonobono.backend.community.article.dto.res.ArticleListResponseDto;
import com.bonobono.backend.community.article.enumclass.ArticleType;
import com.bonobono.backend.community.article.service.ArticleCommentLikeService;
import com.bonobono.backend.community.article.service.ArticleCommentService;
import com.bonobono.backend.community.article.service.ArticleLikeService;
import com.bonobono.backend.community.article.service.ArticleService;
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

@Tag(name = "together", description = "함께게시판")
@RequiredArgsConstructor
@RestController
@RequestMapping("/community/together")
public class ArticleTogetherController {

    private final ArticleService articleService;

    private final ArticleCommentService articleCommentService;

    private final ArticleLikeService articleLikeService;

    private final ArticleCommentLikeService articleCommentLikeService;

    private final ArticleType type = ArticleType.TOGETHER;

    @Operation(summary = "함께게시판 글쓰기")
    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> save(@RequestPart ArticleSaveRequestDto requestDto,
                                     @RequestPart(value = "imageFiles", required = false) List<MultipartFile> imageFiles){
        articleService.save(type, SecurityUtil.getLoginMemberId(), requestDto, imageFiles);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "함께게시판 전체 글 조회")
    @GetMapping("")
    public ResponseEntity<List<ArticleListResponseDto>> findAllDesc(){
        List<ArticleListResponseDto> responseDto =  articleService.findAllDesc(type);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "함께게시판 특정 글, 글에 관한 댓글 조회하기")
    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleDetailResponseDto> findById(@PathVariable Long articleId) {
        // @AuthenticationPrincipa 사용하기
        ArticleDetailResponseDto responseDto = articleService.findById(type, SecurityUtil.getLoginMemberId(), articleId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "함께게시판 게시글 검색", description = "키워드가 제목, 내용 포함")
    @GetMapping("/search")
    public ResponseEntity<List<ArticleListResponseDto>> search(@RequestParam("keyword") String keyword){
        List<ArticleListResponseDto> responseDto = articleService.search(type, keyword);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @Operation(summary = "함께게시판 특정 글 수정")
    @PatchMapping(value = "/{articleId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long articleId,
        @RequestPart ArticleUpdateRequestDto requestDto,
        @RequestPart(value = "imageFiles", required = false) List<MultipartFile> imageFiles){
        articleService.update(SecurityUtil.getLoginMemberId(), articleId , requestDto, imageFiles);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "함께게시판 모집 완료처리")
    @PatchMapping("/{articleId}/recruit-complete")
    public ResponseEntity<Void> updateRecruitStatus(@PathVariable Long articleId){
        articleService.updateRecruitStatus(SecurityUtil.getLoginMemberId(), articleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "함께게시판 특정 글 삭제")
    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> delete(@PathVariable Long articleId){
        articleService.delete(SecurityUtil.getLoginMemberId(), articleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ----------댓글---------
    @Operation(summary = "함께게시판 글에 댓글 쓰기")
    @PostMapping("/{articleId}/comment")
    public ResponseEntity<ArticleCommentResponseDto> saveComment(@PathVariable Long articleId,
        @RequestBody ArticleCommentRequestDto requestDto){
        ArticleCommentResponseDto responseDto = articleCommentService.save(SecurityUtil.getLoginMemberId(), articleId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "함께게시판 댓글 삭제")
    @DeleteMapping("/{articleId}/comment/{commentId}")
    public ResponseEntity<Void> deleteComment( @PathVariable Long articleId,
        @PathVariable Long commentId){
        articleCommentService.delete(SecurityUtil.getLoginMemberId(), articleId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "함께게시판 댓글 수정")
    @PatchMapping("/{articleId}/comment/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable Long articleId,
        @PathVariable Long commentId,
        @RequestBody ArticleCommentRequestDto requestDto){
        articleCommentService.update(SecurityUtil.getLoginMemberId(), articleId, commentId, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "함께게시판 좋아요")
    @PatchMapping("/{articleId}/like")
    public ResponseEntity<Void> like(@PathVariable Long articleId) {
        articleLikeService.like(SecurityUtil.getLoginMemberId(), articleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "함께게시판 댓글 좋아요")
    @PatchMapping("/{articleId}/comment/{commentId}/like")
    public ResponseEntity<Void> like(@PathVariable Long articleId,
        @PathVariable Long commentId) {
        articleCommentLikeService.like(SecurityUtil.getLoginMemberId(), articleId, commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
