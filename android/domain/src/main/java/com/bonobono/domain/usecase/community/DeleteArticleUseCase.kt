package com.bonobono.domain.usecase.community

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.repository.community.CommunityRepository
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(type: String, articleId: Long): NetworkResult<Unit> {
        return communityRepository.deleteArticle(type, articleId)
    }
}