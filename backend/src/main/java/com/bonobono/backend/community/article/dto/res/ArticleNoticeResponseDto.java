package com.bonobono.backend.community.article.dto.res;

import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.enumclass.ArticleType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ArticleNoticeResponseDto {

    private ArticleType type;
    private String title;
    private String content;
    private Long memberId;
    private String nickname;
    private String profileImg;
    private int views;
    private String urlTitle;
    private String url;
    private List<ArticleImageResponseDto> images;
    private LocalDateTime createdDate;

    public ArticleNoticeResponseDto(Article entity) {
        this.type = entity.getType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.memberId = entity.getMember().getId();
        this.nickname = entity.getMember().getNickname();
        this.profileImg = entity.getMember().getProfileImg().getImageUrl();
        this.views = entity.getViews();
        this.urlTitle = entity.getUrlTitle();
        this.url = entity.getUrl();
        this.images = entity.getImages().stream()
                .map(ArticleImageResponseDto::new)
                .collect(Collectors.toList());
        this.createdDate = entity.getCreatedDate();
    }
}
