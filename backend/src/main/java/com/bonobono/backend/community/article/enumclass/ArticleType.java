package com.bonobono.backend.community.article.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArticleType {
    FREE("자유"),
    TOGETHER("함께"),
    NOTICE("공지사항")
    ;

    private final String name;
}
