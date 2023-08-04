package com.bonobono.data.model.community

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommentResponse(
    val childComments: List<CommentResponse>,
    val content: String,
    val liked: Boolean,
    val likes: Int,
    val nickname: String,
    val parentCommentId: Int,
): Parcelable