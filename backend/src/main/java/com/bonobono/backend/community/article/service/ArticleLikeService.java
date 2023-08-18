package com.bonobono.backend.community.article.service;

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
    private final ArticleLikeRepository articleLikeRepository;
    private final MemberRepository memberRepository;


    // 게시글 좋아요
    @Transactional
    public void like(Long memberId, Long articleId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다. id =" + memberId));

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID 값을 가진 게시글이 없습니다 + id = " + articleId));

        ArticleLike articleLike = articleLikeRepository.findByArticleAndMember(article, member);
        if(articleLike != null){
            // 이미 좋아요한 상태이면 취소
            articleLikeRepository.delete(articleLike);
        } else {
            ArticleLike newArticleLike = ArticleLike.builder()
                    .article(article)
                    .member(member)
                    .build();
            articleLikeRepository.save(newArticleLike);
        }
    }


}

