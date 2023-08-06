package com.bonobono.backend.community.article.dto.res;

import com.bonobono.backend.community.article.entity.ArticleComment;
import com.bonobono.backend.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ArticleCommentResponseDto {
    private String content;
    private String nickname;
    private List<ArticleCommentResponseDto> childComments = new ArrayList<>();
    private int likes;
    private boolean isLiked;
    private Long parentCommentId;


    public ArticleCommentResponseDto(ArticleComment entity, Member member){
        this.content = entity.getContent();
        this.nickname = entity.getMember().getNickname();
        this.childComments = entity.getChildComments().stream()
                .map(childComment -> new ArticleCommentResponseDto(childComment, member))
                .collect(Collectors.toList());
        this.likes = entity.getArticleCommentLikes().size();
        this.isLiked = entity.getArticleCommentLikes().stream().anyMatch(like -> like.getMember().getId().equals(member.getId()));
        this.parentCommentId = entity.getParentComment() == null ? null : entity.getParentComment().getId();
    }
}
