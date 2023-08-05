package com.bonobono.domain.model.community

import java.util.Date

data class Article(
    val type: String,
    val title: String,
    val content: String,
    val imageSize: Int = 0,
    val mainImage: Image? = null,
    val images: List<Image> = emptyList(),
    val commentCnt: Int,
    val comments: List<Comment>? = null,
    val liked: Boolean = false,
    val likes: Int,
    val nickname: String,
    val profileUrl: String = "",
    val recruitStatus: Boolean,
    val url: String = "",
    val urlTitle: String = "",
    val views: Int = 0,
    val createdAt: Date = Date(),
)