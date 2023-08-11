package com.bonobono.domain.model.community

data class Article(
    val memberId: Long = 0,
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
    val views: Int = 0,
    val createdDate: String = "",
    // 일반 게시판
    val articleId: Long? = null,
    val type: String? = null,
    // 신고 게시판
    val reportId: Long? = null,
    val locationName: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val locationId: Long? = null,
    var adminConfirmStatus: Boolean? = null,
    // 함께 게시판
    val url: String? = null,
    val urlTitle: String? = null,
    var recruitStatus: Boolean? = null
)