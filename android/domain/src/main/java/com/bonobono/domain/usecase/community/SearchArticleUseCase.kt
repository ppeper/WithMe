package com.bonobono.domain.usecase.community

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.community.Article
import com.bonobono.domain.repository.community.CommunityRepository
import javax.inject.Inject

class SearchArticleUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(type: String, keyword: String): List<Article> {
        return communityRepository.queryArticle(type, keyword)
    }
}