package com.bonobono.data.repository.community

import com.bonobono.data.mapper.toDomain
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

    override suspend fun getArticleById(type: String, articleId: Int): NetworkResult<Article> {
        return handleApi { communityService.getArticleById(type, articleId).toDomain() }
    }

    override suspend fun writeArticle(type: String, article: Article, images: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteArticle(type: String, articleId: Int) {
        handleApi { communityService.deleteArticle(type, articleId) }
    }

    override suspend fun updateArticle(type: String, articleId: Int) {
        handleApi { communityService.updateArticle(type, articleId) }
    }

    override suspend fun writeComment(type: String, articleId: Int, comment: Comment) {
        TODO("Not yet implemented")
    }

    override suspend fun updateArticleLike(type: String, articleId: Int) {
        handleApi { communityService.updateArticleLike(type, articleId) }
    }

    override suspend fun updateCommentLike(type: String, articleId: Int, commentId: Int) {
        handleApi { communityService.updateCommentLike(type, articleId, commentId) }
    }

    override suspend fun updateComment(type: String, articleId: Int, commentId: Int) {
        handleApi { communityService.updateComment(type, articleId, commentId) }
    }

    override suspend fun deleteComment(type: String, articleId: Int, commentId: Int) {
        handleApi { communityService.deleteComment(type, articleId, commentId) }
    }

    override suspend fun queryArticle(type: String, keyword: String) {
        handleApi { communityService.queryArticle(type, keyword) }
    }

}
