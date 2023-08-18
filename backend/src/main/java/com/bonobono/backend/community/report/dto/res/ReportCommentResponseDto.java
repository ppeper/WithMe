package com.bonobono.backend.community.report.dto.res;

import com.bonobono.backend.community.report.entity.ReportComment;
import com.bonobono.backend.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ReportCommentResponseDto {
    private String content;
    private Long memberId;
    private String nickname;
    private String profileImg;
    private List<ReportCommentResponseDto> childComments = new ArrayList<>();
    private Long parentCommentId;
    private LocalDateTime createdDate;


    public ReportCommentResponseDto(ReportComment entity, Member member){
        this.content = entity.getContent();
        this.memberId = entity.getMember().getId();
        this.nickname = entity.getMember().getNickname();
        this.profileImg = entity.getMember().getProfileImg().getImageUrl();
        this.childComments = entity.getChildComments().stream()
                .map(childComment -> new ReportCommentResponseDto(childComment, member))
                .collect(Collectors.toList());
        this.parentCommentId = entity.getParentComment() == null ? null : entity.getParentComment().getId();
        this.createdDate = entity.getCreatedDate();
    }
}
