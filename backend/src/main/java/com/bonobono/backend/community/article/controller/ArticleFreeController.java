package com.bonobono.backend.community.article.controller;

import com.bonobono.backend.community.article.dto.req.ArticleCommentRequestDto;
import com.bonobono.backend.community.article.dto.req.ArticleSaveRequestDto;
import com.bonobono.backend.community.article.dto.req.ArticleUpdateRequestDto;
import com.bonobono.backend.community.article.dto.res.*;
import com.bonobono.backend.community.article.enumclass.ArticleType;
import com.bonobono.backend.community.article.service.ArticleCommentLikeService;
import com.bonobono.backend.community.article.service.ArticleCommentService;
import com.bonobono.backend.community.article.service.ArticleLikeService;
import com.bonobono.backend.community.article.service.ArticleService;
import com.bonobono.backend.member.dto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/community/free")
public class ArticleFreeController {

    private final ArticleService articleService;

    private final ArticleCommentService articleCommentService;

    private final ArticleLikeService articleLikeService;

    private final ArticleCommentLikeService articleCommentLikeService;
    private final ArticleType type = ArticleType.FREE;

    // 자유게시판 글쓰기
    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@RequestPart ArticleSaveRequestDto requestDto, @RequestPart(value = "imageFiles", required = false) List<MultipartFile> imageFiles){
        articleService.save(type, requestDto, imageFiles);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    // 자유게시판 전체 글 조회
    @GetMapping("")
    public ResponseEntity<List<ArticleListResponseDto>> findAllDesc(){
        List<ArticleListResponseDto> responseDto =  articleService.findAllDesc(type);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 자유게시판 특정 글, 글에 관한 댓글 조회하기
    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleDetailResponseDto> findById(@PathVariable Long articleId, @RequestBody MemberRequestDto memberRequestDto) {
        // @AuthenticationPrincipal 사용하기
        ArticleDetailResponseDto responseDto = articleService.findById(type, articleId, memberRequestDto.getMemberId());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 자유게시판 게시글 검색 (키워드가 제목, 내용 포함)
    @GetMapping("/search")
    public ResponseEntity<List<ArticleListResponseDto>> search(@RequestParam("keyword") String keyword){
        List<ArticleListResponseDto> responseDto = articleService.search(type, keyword);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    // 자유게시판 특정 글 수정
    @PatchMapping(value = "/{articleId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long articleId,
                                       @RequestPart ArticleUpdateRequestDto requestDto,
                                       @RequestPart(value = "imageFiles", required = false) List<MultipartFile> imageFiles){
        articleService.update(articleId , requestDto, imageFiles);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 자유게시판 특정 글 삭제
    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> delete(@PathVariable Long articleId){
        articleService.delete(articleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // ----------댓글---------
    // 자유게시판 글에 댓글 쓰기
    @PostMapping("/{articleId}/comment")
    public ResponseEntity<Void> saveComment(@PathVariable Long articleId, @RequestBody ArticleCommentRequestDto requestDto){
        articleCommentService.save(articleId, requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 자유게시판 댓글 삭제
    @DeleteMapping("/{articleId}/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long articleId, @PathVariable Long commentId){
        articleCommentService.delete(articleId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 자유게시판 댓글 수정
    @PatchMapping("/{articleId}/comment/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable Long articleId, @PathVariable Long commentId, @RequestBody ArticleCommentRequestDto requestDto){
        articleCommentService.update(articleId, commentId, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 자유게시판 좋아요
    @PatchMapping("/{articleId}/like")
    public ResponseEntity<Void> like(@PathVariable Long articleId, @RequestBody MemberRequestDto requestDto) {
        articleLikeService.like(articleId, requestDto.getMemberId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 자유게시판 댓글 좋아요
    @PatchMapping("/{articleId}/comment/{commentId}/like")
    public ResponseEntity<Void> like(@PathVariable Long articleId, @PathVariable Long commentId, @RequestBody MemberRequestDto requestDto) {
        articleCommentLikeService.like(articleId, commentId, requestDto.getMemberId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
