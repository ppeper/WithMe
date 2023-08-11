package com.bonobono.data.model.community.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.util.Date

@Parcelize
data class ArticleDetailResponse(
    val memberId: Long,
    val articleId: Long,
    val type: String,
    val title: String,
    val content: String,
    val comments: List<CommentResponse>,
    val likes: Int,
    val nickname: String,
    val profileImg: String?,
    val commentCnt: Int,
    val images: List<ImageResponse>?,
    val liked: Boolean,
    val recruitStatus: Boolean,
    val url: String,
    val urlTitle: String,
    val views: Int,
    val createdDate: String,
): Parcelable