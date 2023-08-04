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
public class ArticleDetailResponseDto {

    private ArticleType type;
    private String title;
    private String content;
    private int views;
    private boolean recruitStatus;
    private String urlTitle;
    private String url;
    private List<ArticleImageResponseDto> images;
    private int likes;
    private boolean isLiked;
    private List<ArticleCommentResponseDto> comments;
    private int commentCnt;
    private String nickname;


    public ArticleDetailResponseDto(Article entity, Member member, List<ArticleCommentResponseDto> comments){
        this.type = entity.getType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.views = entity.getViews();
        this.recruitStatus = entity.isRecruitStatus();
        this.urlTitle = entity.getUrlTitle();
        this.url = entity.getUrl();
        this.images = entity.getImages().stream()
                .map(ArticleImageResponseDto::new)
                .collect(Collectors.toList());
        this.likes = entity.getArticleLikes().size();
        this.isLiked =  entity.getArticleLikes().stream().anyMatch(like -> like.getMember().getId().equals(member.getId()));
        this.comments = comments;
        this.commentCnt = entity.getComments().size();
        this.nickname = entity.getMember().getNickname();

    }

}
