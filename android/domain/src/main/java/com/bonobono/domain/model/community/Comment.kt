package com.bonobono.domain.model.community

import java.util.Date

data class Comment(
    val parentCommentId: Int?,
    val content: String,
    val nickname: String,
    val profileUrl: String,
    val childComments: List<Comment>,
    val liked: Boolean,
    val likes: Int,
    val createdAt: Date
)