package com.bonobono.domain.usecase.community

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.repository.community.CommunityRepository
import javax.inject.Inject

class AdminCompleteUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
){
    suspend operator fun invoke(articleId: Long): NetworkResult<Unit> {
        return communityRepository.adminComplete(articleId)
    }
}