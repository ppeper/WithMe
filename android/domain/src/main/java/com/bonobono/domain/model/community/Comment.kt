package com.bonobono.domain.model.community

data class Comment(
    val content: String,
    val memberId: Int,
    val parentCommentId: Int
)