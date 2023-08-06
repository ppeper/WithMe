package com.bonobono.backend.community.article.service;

import com.bonobono.backend.community.article.dto.res.ArticleLikeResponseDto;
import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleLike;
import com.bonobono.backend.community.article.repository.ArticleLikeRepository;
import com.bonobono.backend.community.article.repository.ArticleRepository;
import com.bonobono.backend.member.domain.Member;
import com.bonobono.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ArticleLikeService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final ArticleLikeRepository articleLikeRepository;

    // 게시글 좋아요
    @Transactional
    public ArticleLikeResponseDto like(Long articleId, Long memberId){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID 값을 가진 게시글이 없습니다 + id = " + articleId));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID 값을 가진 Member가 없습니다 + id = " + memberId));

        ArticleLike existingArticleLike = articleLikeRepository.findByArticleAndMember(article, member);
        boolean isLiked;
        if(existingArticleLike != null){
            // 이미 좋아요한 상태이면 취소
            articleLikeRepository.delete(existingArticleLike);
            isLiked = false;
        } else {
            ArticleLike newArticleLike = ArticleLike.builder()
                    .article(article)
                    .member(member)
                    .build();
            articleLikeRepository.save(newArticleLike);
            isLiked = true;
        }

        int updatedLikeCount = articleLikeRepository.countByArticle(article);
        return new ArticleLikeResponseDto(updatedLikeCount, isLiked);
    }


    // 게시글 좋아요 확인
    @Transactional(readOnly = true)
    public Boolean checkLiked(Article article, Member member){
        boolean isLiked = articleLikeRepository.existsByArticleAndMember(article, member);
        return isLiked;
    }

}

