package com.bonobono.data.model.community.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommentRequest(
    val parentCommentId: Long?,
    val content: String
): Parcelable
