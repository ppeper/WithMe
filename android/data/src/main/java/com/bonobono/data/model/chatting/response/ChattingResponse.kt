package com.bonobono.data.model.chatting.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChattingResponse (
    val nickname : String,
    val roomNumber : Int,
    val profileImgName : String,
    val profileImgUrl : String,
    val messages : List<ChatMessageResponse>
) : Parcelable