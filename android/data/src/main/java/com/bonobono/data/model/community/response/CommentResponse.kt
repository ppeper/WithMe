package com.bonobono.data.model.community.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.util.Date

@Parcelize
data class CommentResponse(
    val id: Int,
    val parentCommentId: Int? = null,
    val content: String,
    val profileImg: String?,
    val childComments: List<CommentResponse>,
    val liked: Boolean,
    val likes: Int,
    val nickname: String,
    val createdDate: LocalDateTime
): Parcelable