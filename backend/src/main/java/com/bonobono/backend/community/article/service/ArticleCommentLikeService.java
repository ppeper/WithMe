package com.bonobono.backend.community.article.service;

import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleComment;
import com.bonobono.backend.community.article.entity.ArticleCommentLike;
import com.bonobono.backend.community.article.repository.ArticleCommentLikeRepository;
import com.bonobono.backend.community.article.repository.ArticleCommentRepository;
import com.bonobono.backend.community.article.repository.ArticleRepository;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ArticleCommentLikeService {

    private final ArticleRepository articleRepository;

    private final ArticleCommentRepository articleCommentRepository;

    private final ArticleCommentLikeRepository articleCommentLikeRepository;

    private final MemberRepository memberRepository;

    // 댓글 좋아요
    @Transactional
    public void like(Long memberId, Long articleId, Long articleCommentId){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id=" + articleId));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다. id =" + memberId));

        ArticleComment articleComment = articleCommentRepository.findById(articleCommentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + articleCommentId));

        ArticleCommentLike articleCommentLike = articleCommentLikeRepository.findByArticleAndMemberAndArticleComment(article, member, articleComment);
        if(articleCommentLike != null){
            // 이미 좋아요한 상태이면 취소
            articleCommentLikeRepository.delete(articleCommentLike);
        } else {
            ArticleCommentLike newArticleCommentLike = ArticleCommentLike.builder()
                    .article(article)
                    .member(member)
                    .articleComment(articleComment)
                    .build();
            articleCommentLikeRepository.save(newArticleCommentLike);
        }
    }


}
