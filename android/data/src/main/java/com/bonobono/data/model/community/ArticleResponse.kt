package com.bonobono.data.model.community

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleResponse(
    val commentCnt: Int,
    val comments: List<CommentResponse>,
    val content: String,
    val liked: Boolean,
    val likes: Int,
    val nickname: String,
    val recruitStatus: Boolean,
    val title: String,
    val type: String,
    val url: String,
    val urlTitle: String,
    val views: Int
): Parcelable