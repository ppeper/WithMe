package com.bonobono.backend.community.article.controller;

import com.bonobono.backend.community.article.dto.res.ArticleTogetherResponseDto;
import com.bonobono.backend.community.article.service.ArticleTogetherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/community/together")
public class ArticleTogetherController {
    private final ArticleTogetherService articleTogetherService;

    // 함께 게시판 전체 조회
    @GetMapping("")
    public List<ArticleTogetherResponseDto> findAllDesc(){
        return articleTogetherService.findAllDesc();
    }


    // 함께 게시판 특정 글 조회
    @GetMapping("/{id}")
    public ArticleTogetherResponseDto findById(@PathVariable Long id){
        return articleTogetherService.findById(id);
    }

    // 함께 게시판 특정 글 삭제

    // 함께 게시판 특정 글 수정

    // 함께 게시판 모집 완료

    // 함께 게시판 게시글 검색

    // 함께 게시판 특정 글 좋아요
}
