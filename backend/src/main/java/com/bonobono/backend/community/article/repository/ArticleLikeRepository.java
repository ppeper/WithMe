package com.bonobono.backend.community.article.repository;

import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.entity.ArticleLike;
import com.bonobono.backend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {

    // 게시글 이미 좋아요한 상태인지 확인
    ArticleLike findByArticleAndMember(Article article, Member member);

}
