package com.bonobono.data.model.community.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class CommentResponse(
    val parentCommentId: Int?,
    val content: String,
    val profileImg: String?,
    val childComments: List<CommentResponse>,
    val liked: Boolean,
    val likes: Int,
    val nickname: String,
    val createdDate: Date
): Parcelable