package com.bonobono.backend.domain.community.article.dto.res;


import com.bonobono.backend.domain.community.article.entity.Article;
import com.bonobono.backend.domain.community.article.enumclass.ArticleType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleFreeListResponseDto {

    private ArticleType type;
    private String title;
    private String content;
    private int likes;
    private int views;
    private String nickname;
    private ArticleImageResponseDto images;
    private int imageSize;

    public ArticleFreeListResponseDto(Article entity) {
        this.type = entity.getType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.likes = entity.getLikes();
        this.views = entity.getViews();
        this.nickname = entity.getMember().getNickname();
        this.images = entity.getImages().stream()
                .map(ArticleImageResponseDto::new)
                .findFirst().orElse(null);
        this.imageSize = entity.getImages().size();
    }
}

