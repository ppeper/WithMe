package com.bonobono.backend.domain.community.article.dto.res;

import com.bonobono.backend.domain.community.article.entity.Article;
import com.bonobono.backend.domain.community.article.enumclass.ArticleType;
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
    private String image;
    private int likes;
    private int views;
    private Long memberId;
    private List<ArticleCommentResponseDto> comments;

    public ArticleFreeDetailResponseDto(Article entity){
        this.type = entity.getType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.image = entity.getImage();
        this.likes = entity.getLikes();
        this.views = entity.getViews();
        this.memberId = entity.getMember().getId();
        this.comments = entity.getComments().stream()
                .map(ArticleCommentResponseDto::new)
                .collect(Collectors.toList());
    }

}
