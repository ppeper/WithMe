package com.bonobono.backend.domain.community.article.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArticleType {
    FREE("자유"),
    TOGETHER("함께")
    ;

    private final String name;
}