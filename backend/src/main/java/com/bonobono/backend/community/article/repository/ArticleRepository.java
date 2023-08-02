package com.bonobono.backend.community.article.repository;

import com.bonobono.backend.community.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    // 게시판 전체 내림차순
    @Query("SELECT a FROM Article a ORDER BY a.id DESC")
     List<Article> findAllDesc();

    // 게시판 제목 내용 키워드 검색 기능
    List<Article> findByTitleContainingOrContentContaining(String title, String content);

    // 게시글 조회수 1 증가 기능
    @Modifying
    @Query("update Article a set a.views = a.views + 1 where a.id = :articleId ")
    void updateView(@Param("articleId") Long articleId);
}
