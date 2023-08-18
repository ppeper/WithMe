package com.bonobono.backend.community.article.dto.res;

import com.bonobono.backend.community.article.entity.ArticleComment;
import com.bonobono.backend.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ArticleCommentResponseDto {

    private Long id;
    private String content;
    private Long memberId;
    private String nickname;
    private String profileImg;
    private List<ArticleCommentResponseDto> childComments = new ArrayList<>();
    private int likes;
    private boolean isLiked;
    private Long parentCommentId;
    private LocalDateTime createdDate;

    public ArticleCommentResponseDto(ArticleComment entity, Member member){
        this.id = entity.getId();
        this.content = entity.getContent();
        this.memberId = entity.getMember().getId();
        this.nickname = entity.getMember().getNickname();
        this.profileImg = entity.getMember().getProfileImg().getImageUrl();
        this.childComments = entity.getChildComments().stream()
                .map(childComment -> new ArticleCommentResponseDto(childComment, member))
                .collect(Collectors.toList());
        this.likes = entity.getArticleCommentLikes().size();
        this.isLiked = entity.getArticleCommentLikes().stream().anyMatch(like -> like.getMember().getId().equals(member.getId()));
        this.parentCommentId = entity.getParentComment() == null ? null : entity.getParentComment().getId();
        this.createdDate = entity.getCreatedDate();
    }
}
