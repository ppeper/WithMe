package com.bonobono.backend.community.article.repository;

import com.bonobono.backend.community.article.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {

    List<ArticleComment> findAllByArticleIdAndParentCommentIsNull(Long articleId);
}
