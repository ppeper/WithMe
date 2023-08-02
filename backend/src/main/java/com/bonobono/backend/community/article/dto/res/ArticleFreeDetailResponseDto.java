package com.bonobono.backend.community.article.dto.res;

import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.enumclass.ArticleType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ArticleFreeDetailResponseDto {

    private ArticleType type;
    private String title;
    private String content;
    private int likes;
    private int views;
    private String nickname;
    private List<ArticleImageResponseDto> images;
    private List<ArticleCommentResponseDto> comments;

    public ArticleFreeDetailResponseDto(Article entity){
        this.type = entity.getType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.likes = entity.getLikes();
        this.views = entity.getViews();
        this.images = entity.getImages().stream()
                .map(ArticleImageResponseDto::new)
                .collect(Collectors.toList());
        this.nickname = entity.getMember().getNickname();
        this.comments = entity.getComments().stream()
                .map(ArticleCommentResponseDto::new)
                .collect(Collectors.toList());
    }

}
