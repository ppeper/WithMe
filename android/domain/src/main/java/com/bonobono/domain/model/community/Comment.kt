package com.bonobono.domain.model.community

data class Comment(
    val id: Long = -1,
    val parentCommentId: Long? = null,
    val content: String,
    val nickname: String? = null,
    val profileImg: String? = null,
    var childComments: List<Comment> = emptyList(),
    val liked: Boolean = false,
    val likes: Int = 0,
    val createdDate: String = ""
)