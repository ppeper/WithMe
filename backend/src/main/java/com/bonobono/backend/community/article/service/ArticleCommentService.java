package com.bonobono.backend.community.article.service;

import com.bonobono.backend.community.article.dto.req.ArticleCommentRequestDto;
import com.bonobono.backend.community.article.dto.res.ArticleCommentResponseDto;
import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.repository.ArticleCommentRepository;
import com.bonobono.backend.community.article.entity.ArticleComment;
import com.bonobono.backend.community.article.repository.ArticleRepository;
import com.bonobono.backend.member.entity.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ArticleCommentService {

    private final ArticleRepository articleRepository;

    private final ArticleCommentRepository articleCommentRepository;

    private final MemberRepository memberRepository;

    // 댓글 작성하기
    @Transactional
    public ArticleCommentResponseDto save(Long articleId, ArticleCommentRequestDto requestDto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId));
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(()-> new IllegalArgumentException("해당 멤버가 없습니다. id=" + requestDto.getMemberId()));

        ArticleComment parentComment;
        if (requestDto.getParentId() != null) {
            parentComment = articleCommentRepository.findByParentId(requestDto.getParentId());
        } else {
            parentComment = null;
        }
        ArticleComment articleComment = articleCommentRepository.save(requestDto.toEntity(article, member, parentComment));
        return new ArticleCommentResponseDto(articleComment);
    }

    // 댓글 수정하기
    @Transactional
    public ArticleCommentResponseDto update(Long articleId, Long commentId, ArticleCommentRequestDto requestDto) {
        if (!articleRepository.existsById(articleId)) {
            throw new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId);
        }
        ArticleComment articleComment = articleCommentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다. id=" + commentId));
        articleComment.updateComment(requestDto.getContent());
        return new ArticleCommentResponseDto(articleComment);
    }

    // 댓글 삭제하기
    @Transactional
    public void delete(Long articleId, Long commentId){
        if (!articleRepository.existsById(articleId)) {
            throw new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId);
        }
        ArticleComment articleComment = articleCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + commentId));
        articleCommentRepository.delete(articleComment);
    }

}
