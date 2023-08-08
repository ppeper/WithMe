package com.bonobono.domain.model.community

import java.time.LocalDateTime

data class Article(
    val articleId: Int = 0,
    val type: String = "",
    val title: String,
    val content: String,
    val imageSize: Int = 0,
    val mainImage: Image? = null,
    val images: List<Image> = emptyList(),
    val commentCnt: Int = 0,
    val comments: List<Comment> = emptyList(),
    val liked: Boolean = false,
    val likes: Int = 0,
    val nickname: String = "",
    val profileImg: String = "",
    val recruitStatus: Boolean = false,
    val url: String = "",
    val urlTitle: String = "",
    val views: Int = 0,
    val createdDate: LocalDateTime = LocalDateTime.now(),
)