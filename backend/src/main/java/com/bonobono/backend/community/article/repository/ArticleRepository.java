package com.bonobono.backend.community.article.repository;

import com.bonobono.backend.community.article.entity.Article;
import com.bonobono.backend.community.article.enumclass.ArticleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    // 게시판 전체 내림차순
    List<Article> findAllByTypeOrderByIdDesc(ArticleType type);

    // 게시판 특정 글 조회
    Optional<Article> findByIdAndType(Long articleId, ArticleType type);

    // 게시판 제목 내용 키워드 검색 기능
    List<Article> findByTypeAndTitleContainingOrContentContaining(ArticleType type, String keyword, String keyword2);

    // 게시글 조회수 1 증가 기능
    @Modifying
    @Query("update Article a set a.views = a.views + 1 where a.id = :articleId ")
    void updateView(@Param("articleId") Long articleId);
}
