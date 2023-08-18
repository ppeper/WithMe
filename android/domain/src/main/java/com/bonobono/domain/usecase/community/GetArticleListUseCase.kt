package com.bonobono.domain.usecase.community

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.community.Article
import com.bonobono.domain.repository.community.CommunityRepository
import javax.inject.Inject

class GetArticleListUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {

    suspend operator fun invoke(type: String): NetworkResult<List<Article>> {
        return communityRepository.getArticleList(type)
    }
}