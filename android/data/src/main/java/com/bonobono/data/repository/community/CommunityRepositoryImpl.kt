package com.bonobono.data.repository.community

import android.net.Uri
import android.util.Log
import com.bonobono.data.mapper.Converter
import com.bonobono.data.mapper.toDomain
import com.bonobono.data.mapper.toModel
import com.bonobono.data.remote.CommunityService
import com.bonobono.data.remote.handleApi
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.community.Article
import com.bonobono.domain.model.community.Comment
import com.bonobono.domain.repository.community.CommunityRepository
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject
import kotlin.math.log

class CommunityRepositoryImpl @Inject constructor(
    private val communityService: CommunityService
) : CommunityRepository {
    override suspend fun getArticleList(type: String): NetworkResult<List<Article>> {
        return handleApi { communityService.getArticleList(type).map { it.toDomain() } }
    }

    override suspend fun getArticleById(type: String, articleId: Int): NetworkResult<Article> {
        return handleApi { communityService.getArticleById(type, articleId).toDomain() }
    }

    override suspend fun writeArticle(type: String, images: List<String>?, article: Article): NetworkResult<Unit> {
        // MultiPart.Body 형태로 변경
        val imageFiles = images?.let { images ->
            images.map { Converter.createMultipartBodyPart(it) }
        }
        val requestDto = article.toModel()
        return handleApi { communityService.writeArticle(communityType = type, images = imageFiles, article = requestDto) }
    }

    override suspend fun deleteArticle(type: String, articleId: Int): NetworkResult<Unit> {
        return handleApi { communityService.deleteArticle(type, articleId) }
    }

    override suspend fun updateArticle(type: String, articleId: Int): NetworkResult<Unit> {
        return handleApi { communityService.updateArticle(type, articleId) }
    }

    override suspend fun writeComment(type: String, articleId: Int, comment: Comment): NetworkResult<Comment> {
        return handleApi { communityService.writeComment(type, articleId, comment.toModel()).toDomain() }
    }

    override suspend fun updateArticleLike(type: String, articleId: Int): NetworkResult<Unit> {
        return handleApi { communityService.updateArticleLike(type, articleId) }
    }

    override suspend fun updateCommentLike(type: String, articleId: Int, commentId: Int): NetworkResult<Unit> {
        return handleApi { communityService.updateCommentLike(type, articleId, commentId) }
    }

    override suspend fun updateComment(type: String, articleId: Int, commentId: Int): NetworkResult<Unit> {
        return handleApi { communityService.updateComment(type, articleId, commentId) }
    }

    override suspend fun deleteComment(type: String, articleId: Int, commentId: Int): NetworkResult<Unit> {
        return handleApi { communityService.deleteComment(type, articleId, commentId) }
    }

    override suspend fun queryArticle(type: String, keyword: String): NetworkResult<List<Article>> {
        return handleApi { communityService.queryArticle(type, keyword).map { it.toDomain() } }
    }

}
