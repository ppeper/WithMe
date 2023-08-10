package com.bonobono.domain.usecase.comment

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.community.Comment
import com.bonobono.domain.repository.community.CommunityRepository
import javax.inject.Inject

class WriteCommentUseCase @Inject constructor(
    private val communityRepository: CommunityRepository
) {
    suspend operator fun invoke(type: String, articleId: Long, comment: Comment): NetworkResult<Comment> {
        return communityRepository.writeComment(type, articleId, comment)
    }
}