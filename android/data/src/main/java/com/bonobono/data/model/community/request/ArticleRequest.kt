package com.bonobono.data.model.community.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleRequest(
    val title: String,
    val content: String,
    // 함께 게시판
    val urlTitle: String? = null,
    val url: String? = null,
    // 신고 게시판
    val latitude: Double?,
    val longitude: Double?,
    val locationId: Long?
): Parcelable