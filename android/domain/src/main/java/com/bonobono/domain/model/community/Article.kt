package com.bonobono.domain.model.community

data class Article(
    val memberId: Long = 0,
    val articleId: Long = 0,
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
    val url: String = "https://",
    val urlTitle: String = "",
    val views: Int = 0,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val createdDate: String = ""
)