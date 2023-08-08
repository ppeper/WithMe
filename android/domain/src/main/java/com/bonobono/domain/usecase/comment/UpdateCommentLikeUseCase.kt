package com.bonobono.domain.usecase.comment

import com.bonobono.domain.repository.community.CommunityRepository
import javax.inject.Inject

class UpdateCommentLikeUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(type: String, articleId: Int, commentId: Int) {
        communityRepository.updateCommentLike(type, articleId, commentId)
    }
}