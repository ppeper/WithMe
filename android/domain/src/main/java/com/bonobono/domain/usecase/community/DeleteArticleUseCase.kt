package com.bonobono.domain.usecase.community

import com.bonobono.domain.repository.community.CommunityRepository
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(type: String, articleId: Int) {
        communityRepository.deleteArticle(type, articleId)
    }
}