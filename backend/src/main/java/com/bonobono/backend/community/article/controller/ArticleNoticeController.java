package com.bonobono.backend.community.article.controller;

import com.bonobono.backend.community.article.enumclass.ArticleType;
import com.bonobono.backend.community.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/community/notice")
public class ArticleNoticeController {

    private final ArticleService articleService;

    private final ArticleType type = ArticleType.NOTICE;

}
