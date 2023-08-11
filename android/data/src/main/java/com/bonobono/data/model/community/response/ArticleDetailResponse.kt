package com.bonobono.data.model.community.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.util.Date

@Parcelize
data class ArticleDetailResponse(
    val memberId: Long,
    val title: String,
    val content: String,
    val comments: List<CommentResponse>,
    val likes: Int,
    val nickname: String,
    val profileImg: String?,
    val commentCnt: Int,
    val images: List<ImageResponse>?,
    val liked: Boolean,
    val views: Int,
    val createdDate: String,
    // 일반 게시판
    val articleId: Long?,
    val type: String?,
    // 신고 게시판
    val reportId: Long?,
    val locationName: String?,
    val latitude: Double?,
    val longitude: Double?,
    val locationId: Long?,
    val adminConfirmStatus: Boolean?,
    // 함께 게시판
    val url: String?,
    val urlTitle: String?,
    val recruitStatus: Boolean?,
): Parcelable
