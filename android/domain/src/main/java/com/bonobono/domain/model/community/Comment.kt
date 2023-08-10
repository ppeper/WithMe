package com.bonobono.domain.model.community

import java.time.LocalDateTime

data class Comment(
    val id: Int = -1,
    val parentCommentId: Int? = null,
    val content: String,
    val nickname: String? = null,
    val profileImg: String? = null,
    var childComments: List<Comment> = emptyList(),
    val liked: Boolean = false,
    val likes: Int = 0,
    val createdDate: LocalDateTime = LocalDateTime.now()
)