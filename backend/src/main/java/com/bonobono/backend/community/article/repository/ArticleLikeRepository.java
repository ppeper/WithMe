package com.bonobono.backend.community.article.repository;

import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleLike;
import com.bonobono.backend.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {

    // 게시글 좋아요 기능
    Optional<ArticleLike> findByMemberAndArticle(Member member, Article article);

    // 게시글 좋아요 취소 기능
    Optional<ArticleLike> deleteArticleLikeByMemberAndArticle(Member member, Article article);
}
