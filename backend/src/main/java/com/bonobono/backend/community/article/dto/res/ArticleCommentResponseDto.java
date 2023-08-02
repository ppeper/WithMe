package com.bonobono.backend.community.article.dto.res;

import com.bonobono.backend.community.article.entity.ArticleComment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ArticleCommentResponseDto {
    private String content;
    private int likes;
    private String nickname;
    private List<ArticleCommentResponseDto> childrenComments = new ArrayList<>();

    public ArticleCommentResponseDto(ArticleComment entity){
        this.content = entity.getContent();
        this.likes = entity.getLikes();
        this.nickname = entity.getMember().getNickname();
        this.childrenComments = entity.getChildrenComments().stream()
                .map(ArticleCommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
