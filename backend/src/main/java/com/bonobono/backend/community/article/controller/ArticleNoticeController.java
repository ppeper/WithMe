package com.bonobono.backend.community.article.controller;

import com.bonobono.backend.community.article.dto.req.ArticleSaveRequestDto;
import com.bonobono.backend.community.article.dto.res.ArticleListResponseDto;
import com.bonobono.backend.community.article.dto.res.ArticleNoticeResponseDto;
import com.bonobono.backend.community.article.enumclass.ArticleType;
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

@Tag(name = "notice", description = "공지사항")
@RequiredArgsConstructor
@RestController
@RequestMapping("/community/notice")
public class ArticleNoticeController {

    private final ArticleService articleService;

    private final ArticleType type = ArticleType.NOTICE;


    @Operation(summary = "공지사항 글 쓰기")
    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@RequestPart ArticleSaveRequestDto requestDto,
                                  @RequestPart(value = "imageFiles", required = false) List<MultipartFile> imageFiles){
        articleService.save(type, SecurityUtil.getLoginMemberId(), requestDto, imageFiles);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "공지사항 전체 글 조회")
    @GetMapping("")
    public ResponseEntity<List<ArticleListResponseDto>> findAllDesc(){
        List<ArticleListResponseDto> responseDto =  articleService.findAllDesc(type);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "공지사항 특정 글, 글에 관한 댓글 조회하기")
    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleNoticeResponseDto> findById(@PathVariable Long articleId) {
        ArticleNoticeResponseDto responseDto = articleService.findNoticeById(type, articleId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
