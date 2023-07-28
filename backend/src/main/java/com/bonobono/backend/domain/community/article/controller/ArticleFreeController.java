package com.bonobono.backend.domain.community.article.controller;

import com.bonobono.backend.domain.community.article.dto.req.ArticleCommentRequestDto;
import com.bonobono.backend.domain.community.article.dto.req.ArticleFreeSaveRequestDto;
import com.bonobono.backend.domain.community.article.dto.res.ArticleFreeDetailResponseDto;
import com.bonobono.backend.domain.community.article.dto.res.ArticleFreeListResponseDto;
import com.bonobono.backend.domain.community.article.service.ArticleCommentService;
import com.bonobono.backend.domain.community.article.service.ArticleFreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/community/article/free")
public class ArticleFreeController {

    private final ArticleFreeService articleFreeService;

    private final ArticleCommentService articleCommentService;

    // 자유게시판 전체 글 조회
    @GetMapping("")
    public List<ArticleFreeListResponseDto> findAllDesc(){
        return articleFreeService.findAllDesc();
    }
    
    // 자유게시판 글쓰기
    @PostMapping("")
    public Long save(@RequestBody ArticleFreeSaveRequestDto requestDto){
        return articleFreeService.save(requestDto);
    }

    // 자유게시판 특정 글과 글에 대한 댓글 조회 (조회수 추가하기)
    @GetMapping("/{id}")
    public ArticleFreeDetailResponseDto findById(@PathVariable Long id){
        return articleFreeService.findById(id);
    }

    // 자유게시판 특정 글 삭제
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id){
        articleFreeService.delete(id);
        return id;
    }

    // 자유게시판 특정 글 수정
    @PatchMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody ArticleFreeSaveRequestDto requestDto){
        return articleFreeService.update(id , requestDto);
    }

    // 자유게시판 게시글 검색 (키워드가 제목, 내용 포함)
    @GetMapping("/search")
    public List<ArticleFreeListResponseDto> search(@RequestParam("keyword") String keyword){
        return articleFreeService.search(keyword);
    }

    // 자유게시판 특정 글 좋아요 (같은 member 좋아요 누르면 취소 되는 것 추가하기)

    // 자유게시판 글에 댓글 쓰기
    @PostMapping("/{id}/comment")
    public Long saveComment(@PathVariable Long id, @RequestBody ArticleCommentRequestDto requestDto){
        return articleCommentService.save(requestDto);
    }
}
