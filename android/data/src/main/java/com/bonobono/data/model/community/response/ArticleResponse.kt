package com.bonobono.data.model.community.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.util.Date

@Parcelize
data class ArticleResponse(
    val articleId: Int,
    val type: String,
    val title: String,
    val content: String,
    val imageSize: Int,
    val images: ImageResponse?,
    val comments: Int,
    val likes: Int,
    val nickname: String,
    val profileImg: String?,
    val recruitStatus: Boolean,
    val url: String? = null,
    val urlTitle: String? = null,
    val views: Int = 0,
    val createdDate: LocalDateTime? = LocalDateTime.now(),
): Parcelable