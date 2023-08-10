package com.bonobono.backend.community.article.controller;

import com.bonobono.backend.community.article.dto.req.ArticleSaveRequestDto;
import com.bonobono.backend.community.article.dto.res.ArticleListResponseDto;
import com.bonobono.backend.community.article.dto.res.ArticleNoticeResponseDto;
import com.bonobono.backend.community.article.enumclass.ArticleType;
import com.bonobono.backend.community.article.service.ArticleService;
import com.bonobono.backend.global.util.SecurityUtil;
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
@RequestMapping("/community/notice")
public class ArticleNoticeController {

    private final ArticleService articleService;

    private final ArticleType type = ArticleType.NOTICE;


    // 공지사항 글 쓰기
    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@RequestPart ArticleSaveRequestDto requestDto,
                                  @RequestPart(value = "imageFiles", required = false) List<MultipartFile> imageFiles){
        articleService.save(type, SecurityUtil.getLoginMemberId(), requestDto, imageFiles);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    // 공지사항 전체 글 조회
    @GetMapping("")
    public ResponseEntity<List<ArticleListResponseDto>> findAllDesc(){
        List<ArticleListResponseDto> responseDto =  articleService.findAllDesc(type);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 공지사항 특정 글, 글에 관한 댓글 조회하기
    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleNoticeResponseDto> findById(@AuthenticationPrincipal Member member,
                                                                         @PathVariable Long articleId) {
        ArticleNoticeResponseDto responseDto = articleService.findNoticeById(type, member, articleId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
