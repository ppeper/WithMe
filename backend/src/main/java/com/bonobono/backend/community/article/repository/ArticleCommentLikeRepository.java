package com.bonobono.backend.community.article.repository;

import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleComment;
import com.bonobono.backend.community.article.entity.ArticleCommentLike;
import com.bonobono.backend.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentLikeRepository extends JpaRepository<ArticleCommentLike, Long> {

    // 게시글 이미 좋아요한 상태인지 확인
    ArticleCommentLike findByArticleAndMemberAndArticleComment(Article article, Member member, ArticleComment articleComment);

    boolean existsByArticleAndMemberAndArticleComment(Article article, Member member, ArticleComment articleComment);

    int countByArticleAndArticleComment(Article article, ArticleComment articleComment);
}
