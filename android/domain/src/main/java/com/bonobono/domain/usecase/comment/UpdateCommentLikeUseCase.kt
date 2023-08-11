package com.bonobono.domain.usecase.comment

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.repository.community.CommunityRepository
import javax.inject.Inject

class UpdateCommentLikeUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(type: String, articleId: Long, commentId: Long): NetworkResult<Unit> {
        return communityRepository.updateCommentLike(type, articleId, commentId)
    }
}