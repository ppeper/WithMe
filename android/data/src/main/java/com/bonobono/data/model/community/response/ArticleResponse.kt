package com.bonobono.data.model.community.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleResponse(
    val title: String,
    val content: String,
    val imageSize: Int,
    val images: ImageResponse?,
    val comments: Int,
    val likes: Int,
    val nickname: String,
    val profileImg: String?,
    val views: Int = 0,
    val createdDate: String,
    // 일반 게시판
    val articleId: Long?,
    val type: String?,
    // 신고 게시판
    val reportId: Long,
    val latitude: Double,
    val longitude: Double,
    val locationId: Long,
    val adminConfirmStatus: Boolean,
    // 함께 게시판
    val url: String,
    val urlTitle: String,
    val recruitStatus: Boolean,
): Parcelable