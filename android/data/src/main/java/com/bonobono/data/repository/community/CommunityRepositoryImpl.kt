package com.bonobono.data.repository.community

import com.bonobono.data.mapper.Converter
import com.bonobono.data.mapper.toDomain
import com.bonobono.data.mapper.toModel
import com.bonobono.data.remote.CommunityService
import com.bonobono.data.remote.handleApi
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.community.Article
import com.bonobono.domain.model.community.Comment
import com.bonobono.domain.repository.community.CommunityRepository
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val communityService: CommunityService
) : CommunityRepository {
    override suspend fun getArticleList(type: String): NetworkResult<List<Article>> {
        return handleApi { communityService.getArticleList(type).map { it.toDomain() } }
    }

    override suspend fun getArticleById(type: String, articleId: Long): NetworkResult<Article> {
        return handleApi { communityService.getArticleById(type, articleId).toDomain() }
    }

    override suspend fun writeArticle(type: String, images: List<String>?, article: Article): NetworkResult<Unit> {
        // MultiPart.Body 형태로 변경
        val imageFiles = images?.let { images ->
            images.map { Converter.createMultipartBodyPart(it) }
        }
        val requestDto = article.toModel()
        return handleApi { communityService.writeArticle(type, images = imageFiles, article = requestDto) }
    }

    override suspend fun deleteArticle(type: String, articleId: Long): NetworkResult<Unit> {
        val response = communityService.deleteArticle(type, articleId)
        return try {
            if (response.isSuccessful) {
                NetworkResult.Success(Unit)
            } else {
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception) {
            e.message?.let { NetworkResult.Error(it) } ?: NetworkResult.Error("Unknown Error Occured")
        }
    }

    override suspend fun recruitComplete(type: String, articleId: Long): NetworkResult<Unit> {
        return handleApi { communityService.recruitComplete(type, articleId) }
    }

    override suspend fun adminComplete(articleId: Long): NetworkResult<Unit> {
        return handleApi { communityService.adminComplete(articleId) }
    }

    override suspend fun updateArticle(type: String, articleId: Long): NetworkResult<Unit> {
        return handleApi { communityService.updateArticle(type, articleId) }
    }

    override suspend fun writeComment(type: String, articleId: Long, comment: Comment): NetworkResult<Comment> {
        return handleApi { communityService.writeComment(type, articleId, comment.toModel()).toDomain() }
    }

    override suspend fun updateArticleLike(type: String, articleId: Long): NetworkResult<Unit> {
        return handleApi { communityService.updateArticleLike(type, articleId) }
    }

    override suspend fun updateCommentLike(type: String, articleId: Long, commentId: Long): NetworkResult<Unit> {
        return handleApi { communityService.updateCommentLike(type, articleId, commentId) }
    }

    override suspend fun updateComment(type: String, articleId: Long, commentId: Long): NetworkResult<Unit> {
        return handleApi { communityService.updateComment(type, articleId, commentId) }
    }

    override suspend fun deleteComment(type: String, articleId: Long, commentId: Long): NetworkResult<Unit> {
        return handleApi { communityService.deleteComment(type, articleId, commentId) }
    }

    override suspend fun queryArticle(type: String, keyword: String): List<Article> {
        return communityService.queryArticle(type, keyword).map { it.toDomain() }
    }

}
