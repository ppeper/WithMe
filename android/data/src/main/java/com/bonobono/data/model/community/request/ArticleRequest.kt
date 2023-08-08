package com.bonobono.data.model.community.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleRequest(
    val title: String,
    val content: String,
    val urlTitle: String?,
    val url: String?
): Parcelable