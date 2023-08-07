package com.bonobono.domain.model.community

import java.util.Date

data class Comment(
    val parentCommentId: Int? = null,
    val content: String,
    val nickname: String? = null,
    val profileImg: String? = null,
    val childComments: List<Comment> = emptyList(),
    val liked: Boolean = false,
    val likes: Int = 0,
    val createdDate: Date = Date()
)