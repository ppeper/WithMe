package com.bonobono.domain.repository.community

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.community.Article
import com.bonobono.domain.model.community.Comment
import java.io.File

interface CommunityRepository {
    // 게시글 전체 목록 가져오기
    suspend fun getArticleList(type: String): NetworkResult<List<Article>>
    // 해당 게시글 가져오기
    suspend fun getArticleById(type: String, articleId: Long): NetworkResult<Article>
    // 일반 게시글 작성
    suspend fun writeArticle(type: String, images: List<String>?, article: Article): NetworkResult<Unit>
    // 게시글 삭제
    suspend fun deleteArticle(type: String, articleId: Long): NetworkResult<Unit>

    // 함께 게시판 모집 완료
    suspend fun recruitComplete(type: String, articleId: Long): NetworkResult<Unit>

    // 신고 게시판 답변 완료
    suspend fun adminComplete(articleId: Long): NetworkResult<Unit>
    // 게시글 수정
    suspend fun updateArticle(type: String, articleId: Long): NetworkResult<Unit>
    // 댓글 작성
    suspend fun writeComment(type: String, articleId: Long, comment: Comment): NetworkResult<Comment>
    // 게시글 좋아요 클릭
    suspend fun updateArticleLike(type: String, articleId: Long): NetworkResult<Unit>
    // 댓글 좋아요 클릭
    suspend fun updateCommentLike(type: String, articleId: Long, commentId: Long): NetworkResult<Unit>
    // 댓글 수정
    suspend fun updateComment(type: String, articleId: Long, commentId: Long): NetworkResult<Unit>
    // 댓글 삭제
    suspend fun deleteComment(type: String, articleId: Long, commentId: Long): NetworkResult<Unit>
    // 게시글 검색
    suspend fun queryArticle(type: String, keyword: String): List<Article>
}