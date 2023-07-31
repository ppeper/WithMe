package com.bonobono.backend.domain.community.article.controller;

import com.bonobono.backend.domain.community.article.dto.req.ArticleCommentRequestDto;
import com.bonobono.backend.domain.community.article.dto.req.ArticleFreeSaveRequestDto;
import com.bonobono.backend.domain.community.article.dto.req.ArticleFreeUpdateRequestDto;
import com.bonobono.backend.domain.community.article.dto.res.ArticleCommentResponseDto;
import com.bonobono.backend.domain.community.article.dto.res.ArticleFreeDetailResponseDto;
import com.bonobono.backend.domain.community.article.dto.res.ArticleFreeListResponseDto;
import com.bonobono.backend.domain.community.article.service.ArticleCommentService;
import com.bonobono.backend.domain.community.article.service.ArticleFreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/community/free")
public class ArticleFreeController {

    private final ArticleFreeService articleFreeService;

    private final ArticleCommentService articleCommentService;

    // 자유게시판 글쓰기
    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ArticleFreeDetailResponseDto> save(@RequestBody ArticleFreeSaveRequestDto requestDto){
        ArticleFreeDetailResponseDto responseDto =  articleFreeService.save(requestDto);
        return new ResponseEntity(responseDto ,HttpStatus.CREATED);
    }

    // 자유게시판 전체 글 조회
    @GetMapping("")
    public ResponseEntity<List<ArticleFreeListResponseDto>> findAllDesc(){
        List<ArticleFreeListResponseDto> responseDto =  articleFreeService.findAllDesc();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 자유게시판 특정 글, 글에 관한 댓글 조회하기
    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleFreeDetailResponseDto> findById(@PathVariable Long articleId){
        ArticleFreeDetailResponseDto responseDto = articleFreeService.findById(articleId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 자유게시판 게시글 검색 (키워드가 제목, 내용 포함)
    @GetMapping("/search")
    public ResponseEntity<List<ArticleFreeListResponseDto>> search(@RequestParam("keyword") String keyword){
        List<ArticleFreeListResponseDto> responseDto = articleFreeService.search(keyword);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    // 자유게시판 특정 글 수정
    @PatchMapping("/{articleId}")
    public ResponseEntity<ArticleFreeDetailResponseDto> update(@PathVariable Long articleId, @RequestBody ArticleFreeUpdateRequestDto requestDto){
        ArticleFreeDetailResponseDto responseDto = articleFreeService.update(articleId , requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 자유게시판 특정 글 삭제
    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> delete(@PathVariable Long articleId){
        articleFreeService.delete(articleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // ----------댓글---------
    // 자유게시판 글에 댓글 쓰기
    @PostMapping("/{articleId}/comment")
    public ResponseEntity<ArticleCommentResponseDto> saveComment(@PathVariable Long articleId, @RequestBody ArticleCommentRequestDto requestDto){
        ArticleCommentResponseDto responseDto = articleCommentService.save(articleId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 자유게시판 댓글 삭제
    @DeleteMapping("/{articleId}/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long articleId, @PathVariable Long commentId){
        articleCommentService.delete(articleId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 자유게시판 댓글 수정
    @PatchMapping("/{articleId}/comment/{commentId}")
    public ResponseEntity<ArticleCommentResponseDto> updateComment(@PathVariable Long articleId, @PathVariable Long commentId, @RequestBody ArticleCommentRequestDto requestDto){
        ArticleCommentResponseDto responseDto = articleCommentService.update(articleId, commentId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    // ----- 기본 CRUD 외 Service 로직들 -----

    /*

    // 자유게시판 특정 글 좋아요 (같은 member 좋아요 누르면 취소 되는 것 추가하기)
    @PatchMapping("/{id}/like")
    public ResponseEntity<Void> li ke(@PathVariable Long id, @AuthenticationPrincipal Member member) {
        result = articleFreeService.addLike(member.getMember(), id);
        return result ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    */

}
