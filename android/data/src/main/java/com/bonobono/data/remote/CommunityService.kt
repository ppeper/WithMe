package com.bonobono.data.remote

import com.bonobono.data.model.community.request.CommentRequest
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.community.Article
import com.bonobono.data.model.community.response.ArticleResponse
import com.bonobono.data.model.community.response.CommentResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface CommunityService {
    // 게시글 전체 조회
    @GET("community/{type}")
    suspend fun getArticleList(
        @Path(value = "type") communityType: String
    ): List<ArticleResponse>

    // articleId 게시글 가져오기
    @GET("community/{type}/{articleId}")
    suspend fun getArticleById(
        @Path(value = "type") communityType: String,
        @Path(value = "articleId") articleId: Int,
    ): ArticleResponse

    // 일반 게시글 등록
    @Multipart
    @POST("community/{type}")
    suspend fun writeArticle(
        @Path(value = "type") communityType: String,
        @Part("imageFiles") images: List<MultipartBody.Part>?,
        @Part("requestDto") article: Article,
    ): NetworkResult<Unit>

    // 게시글 삭제
    @DELETE("community/{type}/{articleId}")
    suspend fun deleteArticle(
        @Path(value = "type") communityType: String,
        @Path(value = "articleId") articleId: Int
    ): Unit

    // 게시글 수정
    @PATCH("community/{type}/{articleId}")
    suspend fun updateArticle(
        @Path(value = "type") communityType: String,
        @Path(value = "articleId") articleId: Int
    ): Unit

    // 게시글 댓글 등록
    @POST("community/{type}/{articleId}/comment")
    suspend fun writeComment(
        @Path(value = "type") communityType: String,
        @Path(value = "articleId") articleId: Int,
        @Body comment: CommentRequest
    ): CommentResponse

    // 게시글 좋아요 클릭
    @PATCH("community/{type}/{articleId}/like")
    suspend fun updateArticleLike(
        @Path(value = "type") communityType: String,
        @Path(value = "articleId") articleId: Int
    ): Unit

    // 댓글 좋아요 클릭
    @PATCH("community/{type}/{articleId}/comment/{commentId}/like")
    suspend fun updateCommentLike(
        @Path(value = "type") communityType: String,
        @Path(value = "articleId") articleId: Int,
        @Path(value = "commentId") commentId: Int
    ): Unit

    // 댓글 수정
    @PATCH("community/{type}/{articleId}/comment/{commentId}")
    suspend fun updateComment(
        @Path(value = "type") communityType: String,
        @Path(value = "articleId") articleId: Int,
        @Path(value = "commentId") commentId: Int
    ): Unit

    // 댓글 삭제
    @DELETE("community/{type}/{articleId}/comment/{commentId}")
    suspend fun deleteComment(
        @Path(value = "type") communityType: String,
        @Path(value = "articleId") articleId: Int,
        @Path(value = "commentId") commentId: Int
    ): Unit

    @GET("community/{type}/search/")
    suspend fun queryArticle(
        @Path(value = "type") communityType: String,
        @Query("keyword") keyword: String
    ): NetworkResult<Unit>
}