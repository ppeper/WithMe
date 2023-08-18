package com.bonobono.backend.community.article.dto.res;

import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.enumclass.ArticleType;
import com.bonobono.backend.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ArticleDetailResponseDto {

    private ArticleType type;
    private String title;
    private String content;
    private Long memberId;
    private String nickname;
    private String profileImg;
    private int views;
    private boolean recruitStatus;
    private String urlTitle;
    private String url;
    private List<ArticleImageResponseDto> images;
    private int likes;
    private boolean isLiked;
    private List<ArticleCommentResponseDto> comments;
    private int commentCnt;
    private LocalDateTime createdDate;



    public ArticleDetailResponseDto(Article entity, Member member, List<ArticleCommentResponseDto> comments){
        this.type = entity.getType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.memberId = entity.getMember().getId();
        this.nickname = entity.getMember().getNickname();
        this.profileImg = entity.getMember().getProfileImg().getImageUrl();
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
        this.createdDate = entity.getCreatedDate();

    }

}
