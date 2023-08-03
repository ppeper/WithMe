package com.bonobono.backend.community.article.dto.res;

import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.enumclass.ArticleType;
import com.bonobono.backend.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ArticleFreeDetailResponseDto {

    private ArticleType type;
    private String title;
    private String content;
    private int views;
    private String nickname;
    private List<ArticleImageResponseDto> images;
    private List<ArticleCommentResponseDto> comments;
    private int likes;
    private boolean isLiked;


    public ArticleFreeDetailResponseDto(Article entity, Member member, List<ArticleCommentResponseDto> comments){
        this.type = entity.getType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.views = entity.getViews();
        this.images = entity.getImages().stream()
                .map(ArticleImageResponseDto::new)
                .collect(Collectors.toList());
        this.nickname = entity.getMember().getNickname();
        this.comments = comments;
        this.likes = entity.getArticleLikes().size();
        this.isLiked =  entity.getArticleLikes().stream().anyMatch(like -> like.getMember().getId().equals(member.getId()));
    }

}
