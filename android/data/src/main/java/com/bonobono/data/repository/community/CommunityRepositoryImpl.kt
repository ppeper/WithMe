package com.bonobono.data.repository.community

import com.bonobono.data.remote.CommunityService
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.community.Article
import com.bonobono.domain.model.community.Comment
import com.bonobono.domain.repository.community.CommunityRepository
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val communityService: CommunityService
) : CommunityRepository {
    override suspend fun getArticleList(type: String): NetworkResult<List<Article>> {
        val response = communityService.getArticleList(type)
        if (response.isSuccessful) {
            response.body()?.let { articleList ->
                return NetworkResult.Success(articleList)
            }
        }
        return NetworkResult.Error(response.message())
    }

    override suspend fun getArticleById(type: String, articleId: Int): NetworkResult<Article> {
        TODO("Not yet implemented")
    }

    override suspend fun writeArticle(type: String, article: Article, images: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteArticle(type: String, articleId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateArticle(type: String, articleId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun writeComment(type: String, articleId: Int, comment: Comment) {
        TODO("Not yet implemented")
    }

    override suspend fun updateArticleLike(type: String, articleId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCommentLike(type: String, articleId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateComment(type: String, articleId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteComment(type: String, articleId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun queryArticle(type: String, articleId: Int) {
        TODO("Not yet implemented")
    }

}
