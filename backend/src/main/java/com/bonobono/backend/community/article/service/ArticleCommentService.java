package com.bonobono.backend.community.article.service;

import com.bonobono.backend.community.article.dto.req.ArticleCommentRequestDto;
import com.bonobono.backend.community.article.dto.res.ArticleCommentResponseDto;
import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleComment;
import com.bonobono.backend.community.article.repository.ArticleCommentRepository;
import com.bonobono.backend.community.article.repository.ArticleRepository;
import com.bonobono.backend.fcm.dto.FCMArticleNotificationRequestDto;
import com.bonobono.backend.fcm.service.FCMNotificationService;
import com.bonobono.backend.global.exception.UserNotAuthorizedException;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleCommentService {

    private final ArticleRepository articleRepository;

    private final ArticleCommentRepository articleCommentRepository;

    private final MemberRepository memberRepository;

    private final FCMNotificationService fcmNotificationService;

    // 댓글, 대댓글 작성하기
    @Transactional
    public ArticleCommentResponseDto save(Long memberId, Long articleId, ArticleCommentRequestDto requestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다. id =" + memberId));
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId));

        ArticleComment parentComment = null;
        if (requestDto.getParentCommentId() != null) {
            parentComment = articleCommentRepository.findById(requestDto.getParentCommentId())
                    .orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다. id=" + requestDto.getParentCommentId()));
        }
        ArticleComment articleComment = articleCommentRepository.save(requestDto.toEntity(article, member, parentComment));
        if (parentComment != null){
            parentComment.addChildComment(articleComment);
        }

        if (!memberId.equals(article.getMember().getId())){
            FCMArticleNotificationRequestDto fcmArticleNotificationRequestDto = FCMArticleNotificationRequestDto.builder()
                    .memberId(article.getMember().getId())
                    .title("게시물에 댓글이 작성되었습니다.")
                    .body(requestDto.getContent())
                    .articleId(articleId)
                    .type(article.getType())
                    .build();

            fcmNotificationService.sendArticleNotificationByToken(fcmArticleNotificationRequestDto);
        }
        return new ArticleCommentResponseDto(articleComment, member);
    }

    // 댓글 조회하기
    @Transactional
    public List<ArticleCommentResponseDto> findByArticleId(Member member, Long articleId){

        return articleCommentRepository.findAllByArticleIdAndParentCommentIsNull(articleId).stream()
                .map(articleComment -> new ArticleCommentResponseDto(articleComment, member))
                .collect(Collectors.toList());
    }

    // 댓글 수정하기
    @Transactional
    public void update(Long memberId, Long articleId, Long commentId, ArticleCommentRequestDto requestDto) {
        if (!articleRepository.existsById(articleId)) {
            throw new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId);
        }
        ArticleComment articleComment = articleCommentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다. id=" + commentId));
        if(memberId.equals(articleComment.getMember().getId())) {
            articleComment.updateComment(requestDto.getContent());
        } else {
            throw new UserNotAuthorizedException("해당 유저는 댓글 작성자가 아닙니다.");
        }
    }

    // 댓글 삭제하기
    @Transactional
    public void delete(Long memberId, Long articleId, Long commentId){
        if (!articleRepository.existsById(articleId)) {
            throw new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId);
        }
        ArticleComment articleComment = articleCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + commentId));
        if(memberId.equals(articleComment.getMember().getId())) {
            articleCommentRepository.delete(articleComment);
        } else {
            throw new UserNotAuthorizedException("해당 멤버는 댓글 작성자가 아닙니다.");
        }
    }

}
