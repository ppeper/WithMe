package com.bonobono.domain.model.community

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")

data class Comment(
    val id: Long = -1,
    val memberId: Long = -1,
    val parentCommentId: Long? = null,
    val content: String,
    val nickname: String? = null,
    val profileImg: String? = null,
    var childComments: List<Comment> = emptyList(),
    val liked: Boolean = false,
    val likes: Int = 0,
    val createdDate: String = LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(formatter).toString()
)